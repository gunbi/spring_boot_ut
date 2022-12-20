package com.gunbi.spring.demo.test.fake;

import com.gunbi.spring.demo.repository.UserRepository;
import org.apache.commons.collections4.IterableUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SqlGroup({
    @Sql(value = "classpath:sql/init.sql", executionPhase = BEFORE_TEST_METHOD)
})
@SpringBootTest
@RunWith(SpringRunner.class)
public class FakeUserBaseTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void initUserNumberIsGreaterThanFive() {
        Assert.assertTrue(IterableUtils.toList(userRepository.findAll()).size() > 5);
    }
}
