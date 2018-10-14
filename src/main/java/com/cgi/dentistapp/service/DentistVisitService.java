package com.cgi.dentistapp.service;

import com.cgi.dentistapp.entity.DentistVisitEntity;
import com.cgi.dentistapp.repositories.DentistVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;


@Service
@Transactional
public class DentistVisitService {

    @Autowired
    DentistVisitRepository repository;

    //Visiidi lisamine
    public boolean addVisit(DentistVisitEntity entity) {
        Iterable<DentistVisitEntity> values = repository.findAll();

        //Kontroll, ega sellist visiiti juba olemas ei ole
        //Kui ei ole siis lisab visiidi
        for (DentistVisitEntity value: values){
            if (checkDates(value.getDate(), entity.getDate()) && value.getDentist().equals(entity.getDentist())){
                return false;
            }
        }
        repository.save(entity);
        return true;
    }

    //Abistav funktsoon, et if statement eelmises funktsioonis liiga pikaks ei veniks
    //Kontrollib ega sellel tunnil pole visiiti
    public boolean checkDates(LocalDateTime date1, LocalDateTime date2){
        if (date1.getHour() == date2.getHour()){
            return true;
        }
        return false;
    }

    //Suht obvious
    public Iterable<DentistVisitEntity> getAllVisits() {
        return repository.findAll();
    }

    //Otsingu põhjal leitavad visiidid
    public ArrayList<DentistVisitEntity> getVisitsFromSearch(String searchable) {
        ArrayList<DentistVisitEntity> matches = new ArrayList<>();
        Iterable<DentistVisitEntity> values = repository.findAll();

        //Kui arsti nimes on otsitavat fraasi, siis sobib otsingusse
        for (DentistVisitEntity value: values){
            if (value.getDentist().contains(searchable)){
                matches.add(value);
            }
        }
        return matches;
    }

    //Ka suht obvious
    public void removeVisit(Integer id){
        repository.delete(id);
    }

    public DentistVisitEntity getVisit(Integer id){
        return repository.findOne(id);
    }
}
