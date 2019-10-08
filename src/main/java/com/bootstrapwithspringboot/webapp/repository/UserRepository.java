package com.bootstrapwithspringboot.webapp.repository;

import com.bootstrapwithspringboot.webapp.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, Long>,JpaSpecificationExecutor<User> {

    User findById(long id);

    void deleteById(Long id);
}