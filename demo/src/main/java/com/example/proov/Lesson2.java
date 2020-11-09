package com.example.proov;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import org.springframework.web.bind.annotation.*;

//@RequestMapping("L2")
@RestController
public class Lesson2 {

    @GetMapping("fibo")     //ex4
    public int fibo(@RequestParam("nr") int nr) {
        return Lesson2.fibonacci(nr);
    }

    @GetMapping("ex1")
    public int[] ex1(@RequestParam("nr") int nr){
        return Lesson2.exercise1(nr);
    }

    @GetMapping("ex2")
    public int[] ex2(@RequestParam("nr") int nr) {
        return Lesson2.exercise2(nr);
    }

    @GetMapping("ex5")
    public String ex5(@RequestParam("nr1") int nr1,
                      @RequestParam("nr2") int nr2) {
        return Lesson2.exercise5(nr1, nr2);
    }

    @GetMapping("ex6")


    public static int[] exercise1(int nr){
        // TODO loo 10 elemendile täisarvude massiv
        // TODO loe sisse konsoolist 10 täisarvu
        // TODO trüki arvud välja vastupidises järiekorras
        int[] a = new int[10];
        int[] b = new int[10];
        System.out.println("Paku 10 arvu: ");
        for (int i = 0; i < 10; i++) {
            Scanner scanner = new Scanner(System.in);
            int line = scanner.nextInt();
            a[i] = line;
            //System.out.println(a[i]);
        }
        int j = 0;
        for (int i = a.length - 1; i >= 0; i--) {
            j++;
            b[j] = a[i];
        }
        return b;
    }

    public static int[] exercise2(int x) {
        // TODO prindi välja x esimest paaris arvu
        // Näide:
        // Sisend 5
        // Väljund 2 4 6 8 10
        int[] väljund = new int[x];
        int n = 0;
        for (int i = 0; i < x; i++) {
            n += 2;
            väljund[i] += n;
            //System.out.print(väljund + " ");
        }
        return väljund;
    }

    public static int exercise3(int x, int y) {
        // TODO trüki välja korrutustabel mis on x ühikut lai ja y ühikut kõrge
        // TODO näiteks x = 3 y = 3
        // TODO väljund
        // 1 2 3
        // 2 4 6
        // 3 6 9
        int k = 0;
        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= y; j++) {
                k = i * j;
                System.out.print(i * j + " ");
            }
            System.out.println();   //tekitab taande pärast igat j-tsükli läbimist
        }
        return k;
    }

    public static int fibonacci(int n) {
        // TODO
        // Fibonacci jada on fib(n) = fib(n-1) + fib(n-2);
        // 0, 1, 1, 2, 3, 5, 8, 13, 21
        // Tagasta fibonacci jada n element

        int a = 0;
        int b = 1;
        int c = 0;
        //int[] jada = new int[n];
        if (n == 1){
            return a;
        } else if (n == 2){
            return b;
        }else {
            for (int i = 2; i < n; i++) {
                c = a + b;
                a = b;
                b = c;
                //jada[i] = c;
                //System.out.print(c + " ");
            }
            return c;
        }
    }

    public static String exercise5(int a, int b) {
        // https://onlinejudge.org/index.php?option=onlinejudge&Itemid=8&page=show_problem&problem=36
        int m = a;
        int n = b;

        int counterM = 1;
        int counterN = 1;
        System.out.print(counterM + " ");
        System.out.println(counterN);

//        if (n <= 1) {
//            System.out.println("STOP");
//            return n;
//        }
        while (n > 1 || m > 1){
            while (m > 1){
                counterM++;
                if (m % 2 != 0){
                    m = 3*m+1;
                    //System.out.println("counterM " + counterM);
                    //System.out.println(m);
                    //System.out.println();
                }else {
                    m = m / 2;
                    //System.out.println("counterM " + counterM);
                    //System.out.println(m);
                    //System.out.println();
                }
            }
            counterN++;
            if (n % 2 != 0){
                n = 3*n+1;
                //System.out.println("counterN " + counterN);
                //System.out.println(n);
                //System.out.println();
            }else{
                n = n/2;
                //System.out.println("counterN " + counterN);
                //System.out.println(n);
                //System.out.println();
            }
        }
        if (counterM > counterN){
            System.out.print(a + " ");
            System.out.print(b + " ");
            System.out.println(counterM);
            return a + " " + b + " " + counterM;
        } if (counterM == counterN){
            System.out.print(a + " ");
            System.out.print(b + " ");
            System.out.println(counterN);
            return a + " " + b + " " + counterM;
        } else {
            System.out.print(a + " ");
            System.out.print(b + " ");
            System.out.println(counterN);
            return a + " " + b + " " + counterN;
        }
    }

    public static String exercise6() throws FileNotFoundException {
        /*
            Kirjutada Java programm, mis loeb failist visits.txt sisse looduspargi külastajad erinevatel jaanuari päevadel ning
            a) sorteerib külastuspäevad külastajate arvu järgi kasvavalt ning prindib tulemuse konsoolile;
            b) prindib konsoolile päeva, mil külastajaid oli kõige rohkem.
            Faili asukoht tuleb programmile ette anda käsurea parameetrina.
         */

        //String a = "dfg gdfg 232";
        //String[] b = a.split(" ");        //teeb Stringi tühiku koha peal pooleks ja vabaneb sellest tühikust
        //System.out.println(b[0]);
        //System.out.println(b[1].trim());    //eemaldab kõik white-space'd (tühikud)
        //Visit visit = new Visit("visits.txt");
        File file = new File("visits.txt");
        Scanner scanner = new Scanner(file);
        List<String[]> visits = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        List<String> date = new ArrayList<>();
        while (scanner.hasNext()) {
            String visit = scanner.nextLine();
            String[] visitArr = visit.split(", ");
            visits.add(visitArr);           //list, kus üks rida on array, kus kpv ja külastajate nr omakorda Stringina
            count.add(Integer.parseInt(visitArr[1]));   //list, kus on kõik külastajate nrd
        }
        //System.out.println(count);
        for (int i = 0; i < visits.size(); i++) {
            String[] temp;
            int min;
            for (int j = 0 + i; j < count.size(); j++) {
                if (count.get(j) < count.get(i)) {
                    temp = visits.get(j);
                    min = count.get(j);
                    visits.remove(j);
                    count.remove(j);
                    visits.add(i, temp);
                    count.add(i, min);
                }
            }
        }
        for (int i = 0; i < visits.size(); i++){
            date.add(visits.get(i)[0]);     //list, kus on kpv-d ümberjärjestatult
        }
        System.out.println(date);
        System.out.println("Kõige rohkem käis külastajaid " + date.get(date.size()-1));
        return date.get(date.size()-1);

        //visits.sort(new Comparator<Visit>()){
        //public int compare(visit 1, visit 2){
        //return 0;
        //}
        //}

        //b1 = 1;
        //b2 = 2;
        //if(b1.compareTo(b2) < 0){
        //System.out.println("true");
        //output: -1; true
//}
    }

    public static void exercise7() {
        // TODO arvuta kasutades BigDecimali 1.89 * ((394486820340 / 15 ) - 4 )
        BigDecimal a = new BigDecimal("1.89");
        BigDecimal b = new BigDecimal("394486820345");
        BigDecimal c = new BigDecimal("15");
        BigDecimal d = new BigDecimal("4");


        System.out.println(b.divide(c, 4, RoundingMode.HALF_UP));
    }

    public static void exercise8() {
        /*
        Failis nums.txt on üksteise all 150 60-kohalist numbrit.
        Kirjuta programm, mis loeks antud numbrid failist sisse ja liidaks need arvud kokku ning kuvaks ekraanil summa.
        Faili nimi tuleb programmile ette anda käsurea parameetrina.
        VASTUS:
        Õige summa: 77378062799264987173249634924670947389130820063105651135266574
         */
    }

    public static void exercise9() {
        /* TODO
        Sama mis eelmises ülesandes aga ära kasuta BigInt ega BigDecimal klassi
         */
    }

}