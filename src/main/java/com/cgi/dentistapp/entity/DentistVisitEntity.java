package com.cgi.dentistapp.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "dentist_visit")
public class DentistVisitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    private String dentist;




    public DentistVisitEntity() {
    }

    public DentistVisitEntity(Date date, String dentist) {
        this.date = date;
        this.dentist = dentist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
