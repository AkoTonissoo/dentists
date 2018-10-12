package com.cgi.dentistapp.service;

import com.cgi.dentistapp.entity.DentistEntity;
import com.cgi.dentistapp.repositories.DentistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DentistService {

    @Autowired
    DentistRepository repository;

    public Iterable<DentistEntity> getAllDentists() {
        return repository.findAll();
    }



}