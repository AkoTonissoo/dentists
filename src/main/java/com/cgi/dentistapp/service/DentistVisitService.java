package com.cgi.dentistapp.service;

import com.cgi.dentistapp.entity.DentistVisitEntity;
import com.cgi.dentistapp.repositories.DentistVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.println;

@Service
@Transactional
public class DentistVisitService {

    @Autowired
    DentistVisitRepository repository;


    public boolean addVisit(DentistVisitEntity entity) {
        //kontrolli kas on olemas
        Iterable<DentistVisitEntity> values = repository.findAll();

        for (DentistVisitEntity value: values){
            if (checkDates(value.getDate(), entity.getDate()) && value.getDentist().equals(entity.getDentist())){
                return false;
            }
        }
        repository.save(entity);
        return true;
    }

    public boolean checkDates(LocalDateTime date1, LocalDateTime date2){
        if (date1.getHour() == date2.getHour()){
            return true;
        }
        return false;
    }

    public Iterable<DentistVisitEntity> getAllVisits() {
        return repository.findAll();
    }

    public void removeVisit(Integer id){
        repository.delete(id);
    }

    public DentistVisitEntity getVisit(Integer id){
        return repository.findOne(id);
    }

    public void SearchVisit(){

    }
}
