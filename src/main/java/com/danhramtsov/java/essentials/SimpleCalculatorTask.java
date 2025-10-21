package com.danhramtsov.java.essentials;
import java.util.Scanner;

public class SimpleCalculatorTask {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        sc.nextLine(); // очистка буфера после int
        String x = sc.nextLine();
        int b = sc.nextInt();

        double result;
        boolean error = false;

        switch (x) {
            case "*":
                result = (double) a * b;
                break;
            case "+":
                result = (double) a + b;
                break;
            case "-":
                result = (double) a - b;
                break;
            case "/":
                if (b == 0) {
                    error = true;
                    result = 0;
                } else {
                    result = (double) a / b;
                }
                break;
            default:
                error = true;
                result = 0;
        }

        if (error) {
            System.out.println("Ошибка!");
        } else {
            System.out.printf("%.2f%n", result);
        }

        sc.close();
    }
}
