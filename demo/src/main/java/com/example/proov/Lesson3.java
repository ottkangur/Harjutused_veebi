package com.example.proov;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class Lesson3 {

    /**public static void main(String[] args) {


        //ül1
        System.out.println(uusSumma(2, 3));
        System.out.println();

        //ül2
        int[] mas = {7, 2, 5, 3, 1, 6};
        System.out.println(sum(mas));
        System.out.println();

        //ül3
        System.out.println(factorial(5));
        System.out.println();

        //ül4
        System.out.println("uus massiiv: " + Arrays.toString(sort(mas)));
        System.out.println();

        //ül5
        System.out.println(reverseString("kalad"));
        System.out.println();

        //ül6
        System.out.println(isPrime(179));

    }
*/
    private static void test(Map<String, String> testMap) {
        testMap.put("test", "test");
    }

    @GetMapping("summa")
    public int summa(int x, int y, int z){ // kui panna nii, siis vaikimisi tähendab (@GetRequest("x") int x, @GetRequest("y") int y)
        return Lesson3.uusSumma(x, y, z);
    }
/**
    @GetMapping("sumArr")
    public int sumArr(@RequestParam("massiiv") int[] massiiv){  //array
        return Lesson3.sum(massiiv);
    }
*/
    public static int uusSumma(int x, int y, int z) {
        // TODO liida kokku ja tagasta x ja y väärtus
        int sum = x + y + z;
        return sum;
    }

    public static int sum(int[] x) {
        // Todo liida kokku kõik numbrid massivis x
        int y = 0;
        for (int i = 0; i < x.length; i++) {
            y += x[i];
        }
        return y;
    }

    @GetMapping("factorialCont")
    public int factorialCont(int x){
        return Lesson3.factorial(x);
    }

    public static int factorial(int x) {
        // TODO tagasta x faktoriaal.
        // Näiteks
        // x = 5
        // return 5*4*3*2*1 = 24
        int faktoriaal = 1;
        for (int i = 1; i <= x; i++) {
            faktoriaal *= i;
            //System.out.println(faktoriaal);
        }
        return faktoriaal;
    }

    public static int[] sort(int[] a) {
        // TODO sorteeri massiiv suuruse järgi
        // Näiteks {2, 6, 8, 1}
        // Väljund {1, 2, 6, 8}

        //int[] b = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            int min = a[i];     //kui j-tsükkel saab tehtud, siis uus min on indeksi i kohal,
                                //et eelmised väikseimad arvud järgmisest j-tsüklist välja jääks
            for (int j = 0 + i; j < a.length; j++) {
                if (a[j] < min) {
                    min = a[j];
                    a[j] = a[i];
                    a[i] = min;     //väikseim väärtus liigub esimeseks
                }
            }
        }
        System.out.println(Arrays.toString(a));
        return a;
    }

    public static String reverseString(String a) {
        // TODO tagasta string tagurpidi
        // Näiteks:
        // a = "Test";
        // return tseT";
        // StringBuilder x = new StrinBuilder()
        // x.append("a")    //saan tähti juurde panna

        // String[] word = new String[a.length()];
        StringBuilder word = new StringBuilder(); //StringBuilderile saab tähti juurde lisada
        for (int i = 0; i < a.length(); i++) {
                word.append(a.substring(a.length() - i - 1, a.length() - i)); //sama ,mis word.append(a, a.length() - i - 1, a.length() - i)
                //word[i] = a.substring(a.length()-i-1, a.length()-i);
                //System.out.println(word);
            //}
        }
        String reverse = word.toString();
        //String b;
        //for (int i = 0; i < a.length(); i++){
            //b += a.substring(a.length()-i-1);
           // System.out.println(b);
        //}
            //System.out.println(Arrays.toString(word));

        //System.out.println(a.substring(0, 1));
        //System.out.println(a.substring(1, 2));
        //System.out.println(a.substring(2, 3));
        //System.out.println(a.substring(3, 4));
        //System.out.println(a.substring(2, 4));
        //System.out.println(a.substring(2));
        String[] array = a.split("ka");
        //System.out.println(array);
        //System.out.println(array[0]);
        //System.out.println(array[1]);
        //System.out.println(a.indexOf("d"));

        return reverse;
    }

    public static boolean isPrime(int x) {
        // TODO tagasta kas sisestatud arv on primaar arv (jagub ainult 1 ja iseendaga)
        for (int i = 2; i < x/2; i++) {   //kui x jagub 2 kuni x/2-ga, siis ei ole primaar
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

}

