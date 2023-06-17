package hr.kingict.akademija2023.springbootakademija2023.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/test")

public class TestController {
    //Razne Get metode na REST api-u
    @GetMapping(value = "/get")
    public String testGet(){
        return "Okej";
    }

    @GetMapping(value = "/get/{param}")
    public String testGetWithParam(@PathVariable String param){
        return param;
    }


}
