package com.bootstrapwithspringboot.webapp.repository;

import java.util.List;

import com.bootstrapwithspringboot.webapp.model.School;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SchoolRepository extends JpaRepository<School, String>, JpaSpecificationExecutor<School> {

   public List<School> findBySchool(String school,Sort sort);
    School findById(long id);
    @Query(value = "select * from school where school.school  in "+
    "(select schools.school from schools where region= :region ) and year = YEAR(sysdate()) order by year desc,Phase desc",
            nativeQuery = true)
    public List<School> queryRegion(@Param("region") String region);

}