package com.example.IDNOcreator.idno.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/page")
public class PageController {
    @RequestMapping(value = "/index")
    public String index(Model model){
        return "index";
    }
}
