package com.example.proov.erandid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    public static void main(String[] args) {

    }

    @GetMapping("exception_test")
    public int exceptionTest(@RequestParam(value = "i", required = false) Integer i){
        return fib(i);
    }

    public static int fib(int n) {
//        if(n == null){
//            throw new ApplicationException("i peab olema number");
//        }
        if (n < 1){
            throw new ApplicationException("i peab olema positiivne");
        }
        if (n ==1){
            return 0;
        }
        if (n == 2){
            return 1;
        }
        //System.out.println("200");    //tagastab konsoolile 200 ehk status code kõik OK
        return fib(n-1) + fib(n-2);
    }

//    public static int fib(int n) {
//        if (n < 1) {
//            throw new ApplicationException("i peab olema positiivne");
//        }
//        if (n == 1) {
//            return 0;
//        } else {
//            for (int i = 2; i < n; i++) {
//                c = a + b;
//                a = b;
//                b = c;
//            }
//            return c;
//        }
//    }
}
