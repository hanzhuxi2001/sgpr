package com.bootstrapwithspringboot.webapp.service.impl;

import java.util.List;

import com.bootstrapwithspringboot.webapp.model.School;
import com.bootstrapwithspringboot.webapp.model.Schools;
import com.bootstrapwithspringboot.webapp.repository.SchoolRegionRepository;
import com.bootstrapwithspringboot.webapp.repository.SchoolRepository;
import com.bootstrapwithspringboot.webapp.service.SchoolService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SchoolServiceImpl implements SchoolService {
    public static final String query="select school from sg.school group by school";

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private SchoolRegionRepository schoolRegionRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<School> findSchoolBySchool(String school) {
        return schoolRepository.findBySchool(school,Sort.by(Sort.Direction.DESC, "year").and(Sort.by("phase")));
    }

    public List<Schools> findAll() {
        return schoolRegionRepository.findAll();
    }

    public List<School> findSchoolByRegion(String region) {
        return schoolRepository.queryRegion(region);
    }

    public List<String> findSchoolName() {
        List<String> data = jdbcTemplate.queryForList(query,String.class);
        return data;
    }

}
