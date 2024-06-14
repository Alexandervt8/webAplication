package com.mycompany.calcula;

import jakarta.ejb.Stateless;

@Stateless
public class CalculadoraServiceImpl implements CalculadoraService {
    @Override
    public double sumar(double num1, double num2) {
        return num1 + num2;
    }

    @Override
    public double restar(double num1, double num2) {
        return num1 - num2;
    }

    @Override
    public double multiplicar(double num1, double num2) {
        return num1 * num2;
    }

    @Override
    public double dividir(double num1, double num2) throws ArithmeticException {
        if (num2 == 0) {
            throw new ArithmeticException("No se puede dividir por cero");
        }
        return num1 / num2;
    }
}
