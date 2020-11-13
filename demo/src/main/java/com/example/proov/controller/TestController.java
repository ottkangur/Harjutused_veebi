package com.example.proov.controller;

import com.example.proov.Lesson3;
import com.example.proov.RongigaReisijad;
import com.example.proov.Test;
import com.example.proov.TestKlass;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//DAY 4
@RestController     //
public class TestController {
    /**
     * @GetMapping(value = "/")    //localhost.8080/ ; @GetMapping asemel võib ka olla @PostMapping @PutMapping
     * public String test() {
     * return "Hello World";
     * }
     */



    @GetMapping("hello")        //localhost.8080/hello
    public String test2() {
        return "hello-hello";
    }

    //läheb tööle URL: employee/tekst?nr=
    @GetMapping("employee/{id}")
    //String tekst = "123";
    public String test3(@PathVariable("id") String tekst,
                        @RequestParam("nr") Integer nr,
                        @RequestParam(value = "testID", required = false) Integer optional) {  //"id" peab kokku langema sellega, mis on employe/ järel
        return tekst + " sobib " + nr;       //
    }

    //  /company/5/employee/8/contract/5
    @GetMapping("company/{comid}/employee/{emid}/contract/{conid}")
    public int url1(@PathVariable("comid") int a,   //"comid" --> {comid} üks rida ülevalpool
                    @PathVariable("emid") int b,
                    @PathVariable("conid") int c) {
        return Lesson3.uusSumma(a, b, c);
    }

    //  /?employeeId=8&somethingElse=tere
    @GetMapping("jdf")
    public String url2(@RequestParam("employeeId") int x,   //@RequestParam tuleb pärast küsimärki
                       @RequestParam("somethingElse") String a) {
        return Test.w(x, a);
    }

    //  /company/6?company=5&a=a&b=b
    @GetMapping("company/{nr}")
    public String url3(@PathVariable("nr") int x,
                       @RequestParam("company") int y,
                       @RequestParam("a") String a,
                       @RequestParam("b") String b) {
        return Test.areng(y, a, b);
    }

    @GetMapping("a/*/a/{a}/{b}/c")      //see on Path, request parameetrid lähevad Pathi järele, st pärast '?'
    public void test(@PathVariable("a") String a,   //soovA     //pole vahet, mis järjekorras PathVariables ja PathParam sulgudes on
                     @RequestParam("a") String aa,  //a=rock    //pole vahet mis järjekorras Request parameetreid sisestada
                     @RequestParam("b") String bb,  //b=roll
                     @PathVariable("b") String b){  //soovB
        System.out.println();
    }
    // /test/a/    *    /a/ {a} / {b} /c
    // /test/a/misiganes/a/soovA/soovB/c?a=rock&b=roll
    //                                  ?b=roll&a=rock


    // DAY 5
    @GetMapping("uusIsik")
    public TestKlass setWorker() {
        TestKlass uus = new TestKlass();
        String a = uus.getNimi();
        int b = uus.getId();
        uus.setNimi("Ott");
        uus.setId(1233);

        return uus;
    }

    @GetMapping("vaikimisi")
    public TestKlass getWorker888() {
        TestKlass uus = new TestKlass();
        uus.getNimi();
        uus.getId();
        return uus;
    }

    @PostMapping("uusIsik")
    public List<TestKlass> setWorker2(@RequestBody TestKlass isik) { //
        List<TestKlass> klassList = new ArrayList<>();
        TestKlass uus = new TestKlass();
        klassList.add(isik);
        uus.setNimi("John Doe");
        uus.setId(0000);
        klassList.add(uus);
        return klassList;
    }

    List<TestKlass> empList = new ArrayList<>();
    // harjutus
    //@RequestMapping("isikud") //ei tasu kasutada, sest selle järel asuvad Mappingud hakkavad kõik sisestama, sh @GetMapping
    @PostMapping("isikud")
    public List<TestKlass> setWorker1(@RequestBody TestKlass isik) { //tagastab listi
        empList.add(isik);
        return empList;
    }

    @GetMapping("isikud")       //kõigi listis olevate töötajate Get
    public List<TestKlass> getworkers() {   //tagastab listi
        return empList;
    }

    @GetMapping("isikud/{id}")  //ühe listis oleva töötaja Get indeksi (0, 1, 2...) alusel
    public TestKlass getWorker(@PathVariable("id") int id) {    //tagastab objekti
        //empList.stream().filter(kes -> kes.getId().);
        return empList.get(id);
    }

    @PutMapping("isikud/{id}")    //ühe listis oleva töötaja asendamine uuega indeksi alusel
    public List<TestKlass> replace(@PathVariable("id") int id,
                                   @RequestBody TestKlass isik) {
        empList.set(id, isik);
        return empList;
    }

    @DeleteMapping("isikud/{id}")   //ühe listis oleva töötaja kustutamine indeksi
    public List<TestKlass> replace(@PathVariable("id") int id) {
        empList.remove(id);
        return empList;
    }

    //harjutamine 8.11
    List<RongigaReisijad> reisijad = new ArrayList<>();
    // elron/reisijad/Tartu-Tallinn?kpv=20201108&kell=1723
    @GetMapping("elron")
    public List<RongigaReisijad> reisijad(){
        return reisijad;
    }

    @PostMapping("elron")       //PostMappingut saab teha vaid Postmanis, brauseris mitte
    public void postReisijad(@RequestBody RongigaReisijad arv) {
        reisijad.add(arv);
        //return reisijad;
    }

    @GetMapping("elron/{id}")
    public RongigaReisijad getLog(@PathVariable("id") int id) {
        return reisijad.get(id);
    }

}