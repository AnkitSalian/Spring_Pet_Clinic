package com.ankit.sfgpetclinic.controller;

import com.ankit.sfgpetclinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VetCotroller {

    private final VetService vetService;

    public VetCotroller(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({"vets", "vets/index", "vets/index.html", "/vets.html"})
    public String listVets(Model model){
        model.addAttribute("vets", vetService.findAll());
        return "vets/index";
    }
}
