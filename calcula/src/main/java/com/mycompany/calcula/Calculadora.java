package com.mycompany.calcula;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Scanner;
import java.util.Properties;

public class Calculadora {

    public static void main(String[] args) {
        try {
            // Configurar las propiedades para el contexto de JNDI
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
            props.put(Context.PROVIDER_URL, "iiop://localhost:16452");

            // Inicializar el contexto de JNDI
            Context context = new InitialContext(props);

            // Buscar el servicio en JNDI
            CalculadoraService calculadoraService = (CalculadoraService) context.lookup("java:global/sitio/CalculadoraService");

            // Interactuar con el usuario
            Scanner scanner = new Scanner(System.in);
            double num1, num2, resultado;
            char operacion;

            System.out.println("Calculadora básica");
            System.out.println("Ingrese el primer número:");
            num1 = scanner.nextDouble();

            System.out.println("Ingrese el segundo número:");
            num2 = scanner.nextDouble();

            System.out.println("Ingrese la operación (+, -, *, /):");
            operacion = scanner.next().charAt(0);

            // Realizar la operación utilizando el servicio de la calculadora
            switch (operacion) {
                case '+':
                    resultado = calculadoraService.sumar(num1, num2);
                    break;
                case '-':
                    resultado = calculadoraService.restar(num1, num2);
                    break;
                case '*':
                    resultado = calculadoraService.multiplicar(num1, num2);
                    break;
                case '/':
                    resultado = calculadoraService.dividir(num1, num2);
                    break;
                default:
                    System.out.println("Error: Operación no válida");
                    return;
            }

            // Mostrar el resultado
            System.out.println("El resultado de la operación es: " + resultado);

        } catch (NamingException e) {
            System.out.println("Error al buscar el servicio de la calculadora: " + e.getMessage());
        }
    }
}


