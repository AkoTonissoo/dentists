package com.cgi.dentistapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "dentists")
public class DentistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Override
    public String toString() {
        return "DentistEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
