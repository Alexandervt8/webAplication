package com.mycompany.calcula;

public interface CalculadoraService {
    double sumar(double num1, double num2);
    double restar(double num1, double num2);
    double multiplicar(double num1, double num2);
    double dividir(double num1, double num2) throws ArithmeticException;
}


