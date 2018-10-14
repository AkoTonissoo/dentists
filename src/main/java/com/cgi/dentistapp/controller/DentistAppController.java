package com.cgi.dentistapp.controller;


import com.cgi.dentistapp.entity.DentistEntity;
import com.cgi.dentistapp.entity.DentistVisitEntity;
import com.cgi.dentistapp.service.DentistService;
import com.cgi.dentistapp.service.DentistVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import java.util.ArrayList;


@Controller
@EnableAutoConfiguration
public class DentistAppController extends WebMvcConfigurerAdapter {



    @Autowired
    private DentistVisitService dentistVisitService;

    @Autowired
    private DentistService dentistService;

    //Edukas registreerimine ja ebaõnnestunud registreerimine(taken)
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
        registry.addViewController("/taken").setViewName("taken");
    }

    //Kuvab visiidid
    @GetMapping("/visits")
    public String showRegistrations(Model model) {
        Iterable<DentistVisitEntity> visits = dentistVisitService.getAllVisits();
        model.addAttribute("visits", visits);
        return "visits";
    }

    //Kuvab otsitud visiidid
    @PostMapping("/visits")
    public String searchRegistrations(@RequestParam String search, DentistVisitEntity visit, Model model) {
        ArrayList<DentistVisitEntity> searches = dentistVisitService.getVisitsFromSearch(search);
        model.addAttribute("search", searches);
        return "search";
    }

    //Kustutab visiidid
    @GetMapping("/visits/delete/{id}")
    public String chooseVisitToDelete(@PathVariable Integer id){
        dentistVisitService.removeVisit(id);
        return "delete";
    }

    //Muutmise jaoks
    @GetMapping("/visits/edit/{id}")
    public String chooseVisitToEdit(@PathVariable("id") Integer id, Model model ) {
        model.addAttribute("visit", dentistVisitService.getVisit(id));
        return "edit";
    }


    //Muutuste kinnitamine
    @PostMapping("/edit")
    public String editVisit(DentistVisitEntity visit, BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        modelMap.addAttribute("visit", visit);
        boolean success = dentistVisitService.addVisit(new DentistVisitEntity(visit.getDate(), visit.getDentist()));
        //Kui uue visiidi loomine õnnestub, siis kustutab vana ära
        if (success){
            dentistVisitService.removeVisit(visit.getId());
            return "redirect:/visits";
        }
        return "redirect:/taken";

    }
    //Esilehe kuvamine
    @GetMapping("/")
    public String showRegisterForm(DentistVisitEntity visit, Model model) {
        Iterable<DentistEntity> dentists = dentistService.getAllDentists();
        model.addAttribute("dentists", dentists);
        return "form";
    }

    //Esilehe formi postitamine
    @PostMapping("/")
    public String postRegisterForm(@Valid DentistVisitEntity visit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        boolean success = dentistVisitService.addVisit(new DentistVisitEntity(visit.getDate(), visit.getDentist()));

        if (success){
            return "redirect:/results";
        }
        return "redirect:/taken";

    }
}
