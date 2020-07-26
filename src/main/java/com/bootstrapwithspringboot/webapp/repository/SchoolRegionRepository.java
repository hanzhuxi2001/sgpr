package com.bootstrapwithspringboot.webapp.repository;

import java.util.List;

import com.bootstrapwithspringboot.webapp.model.Schools;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SchoolRegionRepository extends JpaRepository<Schools, String>, JpaSpecificationExecutor<Schools> {

   public List<Schools> findBySchool(String regison,Sort sort);
   public List<Schools> findAll();
   Schools findById(long id);
}