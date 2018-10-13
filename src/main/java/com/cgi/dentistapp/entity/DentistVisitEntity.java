package com.cgi.dentistapp.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "dentist_visit")
public class DentistVisitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy HH")//TODO Tee see formis mingi datepickeri ja asjadega mitte tekstiga kunagi
    private Date date;

    @Size(min = 1, max = 50)
    private String dentist;




    public DentistVisitEntity() {
    }

    public DentistVisitEntity(Date date, String dentist) {
        this.date = date;
        this.dentist = dentist;
    }

    public DentistVisitEntity(Integer id, Date date, String dentist) {
        this.id = id;
        this.date = date;
        this.dentist = dentist;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDentist() {
        return dentist;
    }

    public void setDentist(String dentist) {
        this.dentist = dentist;
    }

    @Override
    public String toString() {
        return "DentistVisitEntity{" +
                "id=" + id +
                ", date=" + date +
                ", dentist='" + dentist + '\'' +
                '}';
    }
}
