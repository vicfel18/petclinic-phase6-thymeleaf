package com.example.petclinic.controller;

import com.example.petclinic.service.OwnerService;
import com.example.petclinic.service.PetService;
import com.example.petclinic.service.VetService;
import com.example.petclinic.service.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    private OwnerService ownerService;
    private PetService petService;
    private VisitService visitService;
    private VetService vetService;

    public WebController(OwnerService ownerService, PetService petService, VisitService visitService, VetService vetService) {
        this.ownerService = ownerService;
        this.petService = petService;
        this.visitService = visitService;
        this.vetService = vetService;
    }

    @GetMapping("/")
    public String hello(Model model) {

        return "index";
    }

    @GetMapping("owner")
    public String showOwners(Model model) {

        model.addAttribute("owners", ownerService.getAllOwners());
        return "owner";
    }

    @GetMapping("addOwner")
    public String addOwner(Model model) {

        return "addowner";
    }

    @GetMapping("pet")
    public String showPets(Model model) {

        model.addAttribute("pets", petService.getAllPets());
        return "pet";
    }

    @GetMapping("visit")
    public String showVisit(Model model) {

        model.addAttribute("visits", visitService.getAllVisits());
        return "visit";
    }

    @GetMapping("vet")
    public String showOwner(Model model) {

        model.addAttribute("vets", vetService.getAllVets());
        return "vet";
    }


}
