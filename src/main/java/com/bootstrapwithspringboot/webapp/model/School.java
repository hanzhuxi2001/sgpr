package com.bootstrapwithspringboot.webapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class School {
    @Id
    // @GeneratedValue
    private long id;
    @Column(nullable = true)
    String school;
    @Column(nullable = false)
    String year;
    @Column(nullable = false)
    String phase;
    @Column(nullable = false)
    String availiability;
    @Column(nullable = false)
    String registration;
    @Column(nullable = false)
    String sucessRate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getAvailiability() {
        return availiability;
    }

    public void setAvailiability(String availiability) {
        this.availiability = availiability;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getSucessRate() {
        return sucessRate;
    }

    public void setSucessRate(String sucessRate) {
        this.sucessRate = sucessRate;
    }

}
