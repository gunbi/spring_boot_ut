package com.gunbi.spring.demo.test.service;


import com.gunbi.spring.demo.entity.User;
import com.gunbi.spring.demo.repository.UserRepository;
import com.gunbi.spring.demo.service.UserService;
import com.gunbi.spring.demo.test.fake.FakeUserBaseTest;
import lombok.RequiredArgsConstructor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

@Service
@RequiredArgsConstructor
@RunWith(SpringRunner.class)
public class UserServiceTestBaseTest extends FakeUserBaseTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUserSuccessThenReturnUser() {
        User newUser = new User();
        newUser.setEmail("test_email");
        newUser.setName("test_name");

        User result = userService.saveOneUser(newUser);

        Assert.assertEquals(result.getEmail(), newUser.getEmail());
        Assert.assertEquals(result.getName(), newUser.getName());

        userRepository.delete(result);
    }
}
