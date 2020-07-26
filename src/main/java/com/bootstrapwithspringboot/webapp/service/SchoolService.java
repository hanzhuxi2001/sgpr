package com.bootstrapwithspringboot.webapp.service;

import java.util.List;

import com.bootstrapwithspringboot.webapp.model.School;
import com.bootstrapwithspringboot.webapp.model.Schools;

public interface SchoolService {
    public List<School> findSchoolBySchool(String school);
    public List<Schools> findAll();
    public List<String> findSchoolName() ;
    public List<School> findSchoolByRegion(String region);

}
