package com.zeraki.zerakitechassessmentbackend.Institution.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Institution Name is required!")
    @Column(name = "institution_name")

    private String institution_name;


    // Getters and Setters.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstitution_name() {
        return institution_name;
    }

    public void setInstitution_name(String institution_name) {
        this.institution_name = institution_name;
    }
}
