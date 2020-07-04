package com.bootstrapwithspringboot.webapp.service;

import java.util.List;

import com.bootstrapwithspringboot.webapp.model.School;

public interface SchoolService {
    public List<School> findSchoolBySchool(String school);
    public List<String> findSchoolName() ;


}
