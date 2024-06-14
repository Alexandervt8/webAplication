package com.mycompany.calcula;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calculadora")
public class CalculadoraServlet extends HttpServlet {

    @EJB
    private CalculadoraService calculadoraService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1>Calculadora</h1>");
        out.println("<form action='calculadora' method='post'>");
        out.println("Número 1: <input type='text' name='num1'><br>");
        out.println("Número 2: <input type='text' name='num2'><br>");
        out.println("Operación: <select name='operacion'>");
        out.println("<option value='+'>+</option>");
        out.println("<option value='-'>-</option>");
        out.println("<option value='*'>*</option>");
        out.println("<option value='/'>/</option>");
        out.println("</select><br>");
        out.println("<input type='submit' value='Calcular'>");
        out.println("</form>");
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            double num1 = Double.parseDouble(request.getParameter("num1"));
            double num2 = Double.parseDouble(request.getParameter("num2"));
            String operacion = request.getParameter("operacion");
            double resultado = 0;

            switch (operacion) {
                case "+":
                    resultado = calculadoraService.sumar(num1, num2);
                    break;
                case "-":
                    resultado = calculadoraService.restar(num1, num2);
                    break;
                case "*":
                    resultado = calculadoraService.multiplicar(num1, num2);
                    break;
                case "/":
                    resultado = calculadoraService.dividir(num1, num2);
                    break;
                default:
                    out.println("Operación no válida");
                    return;
            }

            out.println("<html><body>");
            out.println("<h1>Resultado</h1>");
            out.println("El resultado de " + num1 + " " + operacion + " " + num2 + " es: " + resultado);
            out.println("</body></html>");

        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}
