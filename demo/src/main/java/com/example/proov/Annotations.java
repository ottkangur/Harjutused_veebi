package com.example.proov;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Annotations {

    List<RongigaReisijad> reisijad = new ArrayList<>();

    //@PathVariable
    //URL: /path/2
//    @GetMapping("path/{id}")
//    public RongigaReisijad meetod(@PathVariable("id") int id) {
//        return reisijad.get(id);
//    }

    //@RequestParam
    //URL: /path/suund=Trt-Tln&kpv=20201108
//    @GetMapping("path")
//    public List<RongigaReisijad> meetod(@RequestParam("suund") String suund,
//                                        @RequestParam("kpv") int kpv) {
//        return reisijad;
//    }

//    //@RequestBody  - pole URL-ga seotud
//    @PostMapping("path")
//    public void postReis(@RequestBody RongigaReisijad reis){
//    }

}

