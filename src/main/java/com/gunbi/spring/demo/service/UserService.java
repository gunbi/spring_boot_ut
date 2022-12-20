package com.gunbi.spring.demo.service;


import com.gunbi.spring.demo.entity.User;
import com.gunbi.spring.demo.repository.UserRepository;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User saveOneUser(User user) {
        return this.repository.save(user);
    }

    public User findOneUser(int userId) {
        return this.repository.findOne(userId);
    }

    public List<User> findAllUser() {
        return IterableUtils.toList(this.repository.findAll())
            .stream()
            .sorted(Comparator.comparingLong(User::getId))
            .collect(Collectors.toList());
    }

    public void deleteOneUser(int userId) {
        this.repository.delete(userId);
    }
}
