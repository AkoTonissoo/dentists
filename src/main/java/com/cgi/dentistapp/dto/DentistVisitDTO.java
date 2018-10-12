package com.cgi.dentistapp.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class DentistVisitDTO {
    //formides peaks kasutama DTO (controller ka) ja entityd tabeliteks vb?

    Integer id;

    @Size(min = 1, max = 50)
    String dentistName;

    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy HH")//TODO Tee see formis mingi datepickeri ja asjadega mitte tekstiga kunagi
    Date visitTime;

    public DentistVisitDTO() {
    }

    public DentistVisitDTO(Integer id, String dentistName, Date visitTime) {
        this.id = id;
        this.dentistName = dentistName;
        this.visitTime = visitTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDentistName() {
        return dentistName;
    }

    public void setDentistName(String dentistName) {
        this.dentistName = dentistName;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }
}
