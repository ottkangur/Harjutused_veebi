package com.company;

import java.util.Scanner;

public class MathUtil {
    private String test;


    public static void main(String[] args) {
       /** //min
        System.out.println(min(2, 6));

        //max
        System.out.println(max(3, 43));

        //abs
        System.out.println(abs(-3));

        //isEven
        System.out.println(isEven(5));

        //min3
        System.out.println(min(2, 0, 4));

        //max3
        System.out.println(max(6, 9, 3));*/

        System.out.println("Paku funktsioon: ");
        Scanner scanner = new Scanner(System.in);
        String funk = scanner.nextLine();
        int nr1 = scanner.nextInt();
        if (funk.equals("abs")) {
            System.out.println(abs(nr1));
        } else if (funk.equals("isEven")) {
            System.out.println(isEven(nr1));
        }else {
            int nr2 = scanner.nextInt();
            if (funk.equals("min")) {
                System.out.println(min(nr1, nr2));
            } else if (funk.equals("max")) {
                System.out.println(max(nr1, nr2));
            }
        }

        //System.out.println(min(nr1, nr2));
    }

    // public int vastus(){


    public void test() {
        System.out.println(test);
    }

    public static int min(int a, int b) {
        // TODO tagasta a ja b väikseim väärtus
        if (a < b) {
            return a;
        }
        return b;

    }

    public static int max(int a, int b) {
        // TODO tagasta a ja b suurim väärtus
        if (a > b) {
            return a;
        }
        return b;
        // return 0;
    }

    public static int abs(int a) {
        // TODO tagasta a absoluut arv
        int y = Math.abs(a); //tekitab abosluutväärtuse
        return y;
    }

    public static boolean isEven(int a) {
        // TODO tagasta true, kui a on paaris arv
        // tagasta false kui a on paaritu arv
        if (a % 2 == 0) {
            return true;
        }
        return false;
    }

    public static int min(int a, int b, int c) {
        // TODO tagasta a, b ja c väikseim väärtus
        if (a < b && a < c) {
            return a;
        } else if (b < a && b < c) {
            return b;
        }
        return c;
    }

    public static int max(int a, int b, int c) {
        // TODO tagasta a, b ja c suurim väärtus
        if (a > b && a > c) {
            return a;
        } else if (b > a && b > c) {
            return b;
        }
        return c;
    }

}