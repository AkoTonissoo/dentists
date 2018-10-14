package com.cgi.dentistapp.repositories;

import com.cgi.dentistapp.entity.DentistVisitEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public interface DentistVisitRepository extends CrudRepository <DentistVisitEntity, Integer>{

}
