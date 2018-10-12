package com.cgi.dentistapp.service;

import com.cgi.dentistapp.dto.DentistVisitDTO;
import com.cgi.dentistapp.entity.DentistVisitEntity;
import com.cgi.dentistapp.repositories.DentistVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class DentistVisitService {

    @Autowired
    DentistVisitRepository repository;


    public void addVisit(DentistVisitEntity entity) {
        repository.save(entity);
        //TODO implementation
    }

    public Iterable<DentistVisitEntity> getAllVisits() {
        return repository.findAll();
    }
    //both of them ID järgi kuidagi
    public void removeVisit(Integer id){
        repository.delete(id);
    }

    public DentistVisitEntity getVisit(Integer id){
        return repository.findOne(id);
    }

    public void SearchVisit(){

    }
}
