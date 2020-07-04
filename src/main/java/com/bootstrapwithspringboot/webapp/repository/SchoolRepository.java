package com.bootstrapwithspringboot.webapp.repository;

import java.util.List;

import com.bootstrapwithspringboot.webapp.model.School;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SchoolRepository extends JpaRepository<School, String>, JpaSpecificationExecutor<School> {

   public List<School> findBySchool(String school,Sort sort);
    School findById(long id);
}