package com.ankit.sfgpetclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VetCotroller {

    @RequestMapping({"vets", "vets/index", "vets/index.html"})
    public String listVets(){

        return "vets/index";
    }
}
