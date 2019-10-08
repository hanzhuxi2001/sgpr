package com.bootstrapwithspringboot.webapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import com.bootstrapwithspringboot.webapp.model.User;
import com.bootstrapwithspringboot.webapp.repository.UserRepository;
import com.bootstrapwithspringboot.webapp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUserList() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void edit(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> findUserNoCriteria(int page, int size) {
        Sort sort = new Sort(Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> findUserCriteria(int page, int size, String type) {
        Sort sort = new Sort(Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<User> specification = (root, criteriaQuery, cb) -> {
            List<Predicate> list = new ArrayList<Predicate>();

            list.add(cb.like(root.get("type").as(String.class), "%" + type + "%"));

            return cb.and(list.toArray(new Predicate[list.size()]));
        };
        Page<User> Users = userRepository.findAll(specification, pageable);

        return Users;

    }

}
