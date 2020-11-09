package com.example.proov;

import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RequestMapping("test")
@RestController
public class TestController2 {
    @GetMapping("a/*/a/{a}/{b}/c")      //see on Path, request parameetrid lähevad Pathi järele, st pärast '?'
    public void test(@PathVariable("a") String a,   //soovA
                     @RequestParam("a") String aa,  //a=rock    //pole vahet mis järjekorras Request parameetreid sisestada
                     @RequestParam("b") String bb,  //b=roll
                     @PathVariable("b") String b){  //soovB
        System.out.println();
    }
}
// /test/a/    *    /a/ {a} / {b} /c
// /test/a/misiganes/a/soovA/soovB/c?a=rock&b=roll
//                                  ?b=roll&a=rock