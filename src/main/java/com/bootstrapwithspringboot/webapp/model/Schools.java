package com.bootstrapwithspringboot.webapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Schools {
    @Id
    // @GeneratedValue
    private long id;
    @Column(nullable = true)
    String school;
    @Column(nullable = false)
    String Region;
    @Column(nullable = false)
    String Chinese;
    @Column(nullable = false)
    String Malay;
    @Column(nullable = false)
    String Tamil;

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

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getChinese() {
        return Chinese;
    }

    public void setChinese(String chinese) {
        Chinese = chinese;
    }

    public String getMalay() {
        return Malay;
    }

    public void setMalay(String malay) {
        Malay = malay;
    }

    public String getTamil() {
        return Tamil;
    }

    public void setTamil(String tamil) {
        Tamil = tamil;
    }
  

}
