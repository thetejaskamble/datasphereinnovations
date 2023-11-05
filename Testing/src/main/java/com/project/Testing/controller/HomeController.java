package com.project.Testing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //Visit index page.
    @GetMapping("/")
    public String index(){
        return "index";
    }

    //Visit about page.
    @GetMapping("/about")
    public String about(){
        return "about";
    }

    //Visit trial page. (To trial views to check how HTML and CSS looks!)
    @GetMapping("/trial")
    public String trail(){
        return "trial";
    }
}
