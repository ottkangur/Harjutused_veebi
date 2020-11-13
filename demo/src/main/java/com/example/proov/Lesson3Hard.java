package com.example.proov;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
// Enne kui seda tegema hakkad tee ära Lesson 2 (välja arvatud ülesanded 6, 8, 9)
public class Lesson3Hard {
    public static void main(String[] args) {
        //randomGame(5);
    }

    @GetMapping("evenfibo")
    public static int evenFibonacci(@RequestParam("nr") int n){
        // TODO liida kokku kõik paaris fibonacci arvud kuni numbrini n
        int a = 0;
        int b = 1;
        int sum = 0;
        for (int i = 3; i <= n; i++) {
            int c = a + b;
            a = b;
            b = c;
            if (c % 2 == 0){
                sum +=c;
            }
        }
        return sum;
    }

    @GetMapping("random")
    public static int randomGame(@RequestParam("nr") int x){
        // TODO kirjuta mäng mis võtab suvalise numbri 0-100, mille kasutaja peab ära arvama
        // iga kord pärast kasutaja sisestatud täis arvu peab programm ütlema kas number oli suurem või väiksem
        // ja kasutaja peab saama uuesti arvata
        // numbri ära aramise korral peab programm välja trükkima mitu katset läks numbri ära arvamiseks
        Random random = new Random();
        int i = random.nextInt(100);
        int counter = 1;
        while (x != i){
            if (x < i){
                System.out.println("Vale! Õige vastus on suurem.");
                counter++;
            } else if (x > i){
                System.out.println("Vale! Õige vastus on väiksem.");
                counter++;
            } else{
                System.out.println("Õige vastus! Arvasid ära " + counter + ". korral");
            }
        }
        //System.out.println(i);
        return counter;
    }

    public static String morseCode(String text){
        // TODO kirjuta programm, mis tagastab sisestatud teksti morse koodis (https://en.wikipedia.org/wiki/Morse_code)
        // Kasuta sümboleid . ja -
        return "";
    }
}
