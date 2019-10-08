package com.bootstrapwithspringboot.webapp.service;

import com.bootstrapwithspringboot.webapp.model.User;

import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    public List<User> getUserList();

    public User findUserById(long id);

    public void save(User user);

    public void edit(User user);

    public void delete(long id);

    Page<User> findUserNoCriteria(int page,int size);

    Page<User> findUserCriteria(int page,int size,String Type);

}
