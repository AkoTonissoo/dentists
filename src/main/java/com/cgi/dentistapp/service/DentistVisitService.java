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


    public void addVisit(String dentistName, Date visitTime) {
        DentistVisitEntity visit = new DentistVisitEntity(visitTime, dentistName);
        repository.save(visit);
        //TODO implementation
    }

    public Iterable<DentistVisitEntity> getAllVisits() {
        return repository.findAll();
    }
}
