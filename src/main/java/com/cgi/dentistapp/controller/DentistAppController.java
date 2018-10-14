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

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.println;

@Controller
@EnableAutoConfiguration
public class DentistAppController extends WebMvcConfigurerAdapter {

    @Autowired
    private DentistVisitService dentistVisitService;

    @Autowired
    private DentistService dentistService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
        registry.addViewController("/taken").setViewName("taken");
    }

    @GetMapping("/visits")
    public String showRegistrations(Model model) {
        Iterable<DentistVisitEntity> visits = dentistVisitService.getAllVisits();
        model.addAttribute("visits", visits);
        return "visits";
    }

    @PostMapping("/visits")
    public String searchRegistrations(@RequestParam String search, DentistVisitEntity visit, Model model) {
        println(search);

        ArrayList<DentistVisitEntity> searches = dentistVisitService.getVisitsFromSearch(search);
        model.addAttribute("search", searches);

        return "search";
    }

    @GetMapping("/visits/delete/{id}")
    public String chooseVisitToDelete(@PathVariable Integer id){
        dentistVisitService.removeVisit(id);
        return "delete";
    }

    @GetMapping("/visits/edit/{id}")
    public String chooseVisitToEdit(@PathVariable("id") Integer id, Model model ) {
        model.addAttribute("visit", dentistVisitService.getVisit(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String editVisit(DentistVisitEntity visit, BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return "form";
        }

        modelMap.addAttribute("visit", visit);

        boolean success = dentistVisitService.addVisit(new DentistVisitEntity(visit.getDate(), visit.getDentist()));

        if (success){
            dentistVisitService.removeVisit(visit.getId());
            return "redirect:/visits";
        }
        return "redirect:/taken";

    }

    @GetMapping("/")
    public String showRegisterForm(DentistVisitEntity visit, Model model) {
        Iterable<DentistEntity> dentists = dentistService.getAllDentists();
        model.addAttribute("dentists", dentists);
        return "form";
    }

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


        //println(dentistVisitService.getAllVisits().toString());

    }
}
