package com.example.reviewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeComtroller {

    @GetMapping
    public String home(){
        return "homepage";
    }

}
