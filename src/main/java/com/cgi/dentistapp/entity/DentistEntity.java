package com.cgi.dentistapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "dentists")
public class DentistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DentistEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
