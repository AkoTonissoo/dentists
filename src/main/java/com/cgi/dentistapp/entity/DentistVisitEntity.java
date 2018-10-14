package com.cgi.dentistapp.entity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "dentist_visit")
public class DentistVisitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;

    @Size(min = 1, max = 50)
    private String dentist;

    public DentistVisitEntity(LocalDateTime date, String dentist) {
        this.date = date;
        this.dentist = dentist;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getDentist() {
        return dentist;
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
