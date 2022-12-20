package com.gunbi.spring.demo.controller;

import com.gunbi.spring.demo.entity.User;
import com.gunbi.spring.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return this.service.saveOneUser(user);
    }

    @GetMapping("/fetch/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User retrieveUser(@PathVariable int id) {
        return this.service.findOneUser(id);
    }

    @GetMapping("/fetchAll")
    @ResponseStatus(HttpStatus.OK)
    public List<User> retrieveUsers() {
        return this.service.findAllUser();
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOneUser(@PathVariable("id") int userId) {
        this.service.deleteOneUser(userId);
    }
}
