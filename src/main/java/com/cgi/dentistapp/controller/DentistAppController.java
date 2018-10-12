package com.cgi.dentistapp.controller;

import com.cgi.dentistapp.dto.DentistVisitDTO;
import com.cgi.dentistapp.entity.DentistEntity;
import com.cgi.dentistapp.entity.DentistVisitEntity;
import com.cgi.dentistapp.service.DentistService;
import com.cgi.dentistapp.service.DentistVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;

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
    }

    @GetMapping("/")
    public String showRegisterForm(DentistVisitDTO dentistVisitDTO, Model model) {
        Iterable<DentistEntity> dentists = dentistService.getAllDentists();
        model.addAttribute("dentists", dentists);
        return "form";
    }

    @GetMapping("/visits")
    public String showRegistrations(Model model) {
        Iterable<DentistVisitEntity> visits = dentistVisitService.getAllVisits();
        model.addAttribute("visits", visits);
        return "visits";
    }
    //kind of kustutab aga peab veel panema sinna confirm buttoni mille abil oleks lihtne tagasi visititele routida
    @RequestMapping(value = "/visits/delete/{id}", method = RequestMethod.GET)
    public String removeVisit(@PathVariable Integer id){
        dentistVisitService.removeVisit(id);
        println(dentistVisitService.getAllVisits().toString());
        return "visits";
    }
    //see töötab ka kindof
    //next step nende kahe asjaga on teha n-ö vaheaken siis kus kas editid v confirmid paska, uus html fail peaks see olema
    //ja sealt võiks edasi juba lõbusalt minna äkki tglt usun küll!!!!P.S mdea miks DTO kasutusel peaks olema üldse :D
    @RequestMapping(value= "/visits/edit/{id}", method = RequestMethod.GET)
    public String editVisit(@PathVariable("id") Integer id, Model model ) {
        model.addAttribute(dentistVisitService.getVisit(id));
        return  "visits";
    }

    @PostMapping("/")
    public String postRegisterForm(@Valid DentistVisitDTO dentistVisitDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Iterable<DentistEntity> dentists = dentistService.getAllDentists();
            model.addAttribute("dentists", dentists);
            return "form";
        }

        dentistVisitService.addVisit(new DentistVisitEntity(dentistVisitDTO.getVisitTime(), dentistVisitDTO.getDentistName()));
        println(dentistVisitService.getAllVisits().toString());
        return "redirect:/results";
    }
}
