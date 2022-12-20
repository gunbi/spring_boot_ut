package com.gunbi.spring.demo.test.service

import com.gunbi.spring.demo.entity.User
import com.gunbi.spring.demo.repository.UserRepository
import com.gunbi.spring.demo.service.UserService
import com.gunbi.spring.demo.test.fake.FakeUserBaseTest
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class UserServiceGroovyTest extends FakeUserBaseTest {

    @Autowired
    private UserService userService

    @Autowired
    private UserRepository userRepository

    @Test
    void "save_user_success_then_return_user"() {
        given:
        def user = new User()
        user.email = "test_email"
        user.name = "test_name"

        when:
        def result = userService.saveOneUser(user)

        then:
        null != result

        cleanup:
        userRepository.delete(result)
    }
}
