package com.cgi.dentistapp.repositories;

import com.cgi.dentistapp.entity.DentistEntity;
import org.springframework.data.repository.CrudRepository;

public interface DentistRepository extends CrudRepository <DentistEntity, Integer> {
}
