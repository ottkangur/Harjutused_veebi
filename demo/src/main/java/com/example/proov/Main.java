package com.example.proov;

public class Main {

    public static int getElement(int[] array, String userInput) {
        int index = Integer.parseInt(userInput);
        return array[index];
    }

    public static void main(String[] args) {

        try {
            int nr = getElement(new int[]{1, 2, 3}, "3");  //siia tuleks sisestada 0, 1 või 2, et viga ei tekiks
            System.out.println(nr);     //
        } catch (NumberFormatException e) {     //kaks järgmist rida kuvatakse, kui sisend pole number
            System.out.println("Input is not a number");
            System.out.println("Message:" + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {    //kuvatakse, kui sisend ei ole 0, 1 või 2
            System.out.println("Input is out of range");
            System.out.println("Message:" + e.getMessage());
        } catch (NullPointerException e) {      //kuvatakse, kui midagi pole sisestatud
            System.out.println("Null pointer, one input was null");
            System.out.println("Message: " + e.getMessage());
        }

        /**int a = 1;
        int b = 1;
        int c = 3;
        System.out.println(a == b);
        System.out.println(a == c);
        a = c;
        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println();
*/
        // public static void excersie2 ()
        // TODO
        // Loo muutujad x1 = 10 ja x2 = 20, vali sobiv andmetüüp
        // Tekita muutuja y1 = ++x1, trüki välja nii x1 kui y1
        // Tekita muutuja y2 = x2++, trüki välja nii x2 ja y2
        // Analüüsi tulemusi
//        int x1 = 10;
//        int x2 = 20;
//        int y1 = ++x1;
//        System.out.println(x1);
//        System.out.println(y1);
//        int y2 = x2++;
//        System.out.println(x2);
//        System.out.println(y2);


        //public static void excersie3(){
        // TODO
        // Loo arvulised muutujad
//        int a = 18 % 3;
//        int b = 19 % 3;
//        int c = 20 % 3;
//        int d = 21 % 3;
//        // Prindi välja kõigi muutujate väärtused
//        System.out.println(a);
//        System.out.println(b);
//        System.out.println(c);
//        System.out.println(d);


        //public static void excersie4()
        // TODO
        // Defineeri String tüüpi muutuja mille sisu oleks "\"\\""
        // Trüki muutuja sisu välja
//
//        String str = "\"\\\"\\\\\"\"";
//        System.out.println(str);

    }


}