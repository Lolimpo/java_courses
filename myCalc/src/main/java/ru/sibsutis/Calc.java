package ru.sibsutis;

import java.util.InputMismatchException;
import java.util.Scanner;

class Calc{
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        Integer num1 = null, num2 = null;
        String oper = null;

        try {
            num1 = scanner.nextInt();
            oper = scanner.next();
            num2 = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Incorrect input.");
        }

        switch(oper) {
            case("+"):
                System.out.printf("%d %s %d = %d", num1, oper, num2, (num1 + num2));
                break;
            case("-"):
                System.out.printf("%d %s %d = %d", num1, oper, num2, (num1 - num2));
                break;
            case("*"):
                System.out.printf("%d %s %d = %d", num1, oper, num2, (num1 * num2));
                break;
            case("/"):
                System.out.printf("%d %s %d = %d", num1, oper, num2, (num1 / num2));
                break;
            default:
                System.out.println("Can't calculate your exercise.");
                break;
        }
    }
}