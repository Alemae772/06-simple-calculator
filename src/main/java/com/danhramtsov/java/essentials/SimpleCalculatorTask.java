package com.danhramtsov.java.essentials;
import java.util.Scanner;
public class SimpleCalculatorTask {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double a = sc.nextDouble();
        sc.nextLine(); // очистка буфера после int
        String x = sc.nextLine();
        double b = sc.nextDouble();
        double result = 0;
        boolean error = false;
        switch (x) {
            case "*":
                System.out.print(a * b);
                break;
            case "+":
                System.out.print(a + b);
                break;
            case "-":
                System.out.print(a - b);
                break;
            case "/":
                if (b == 0) {
                    System.out.print("Ошибка!");
                } else {
                    System.out.print(a / b);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + x);
        }
        if (error) {
            System.out.println("Ошибка!");
        } else {
          System.out.printf("%.2f%n", result);
        }
        sc.close();
    }
}

