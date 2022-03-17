package kr.co.aske.blog_api.user.service;

import kr.co.aske.blog_api.user.domain.UserRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService service;


    @Test
    void loadUserByUsername() {

    }

    /*
     *
     */
    @Test
    @DisplayName("이메일이 empty")
    @Transactional
    void save_email_eq_empty() {
        Throwable exception = Assertions.assertThrows(RuntimeException.class,
                () ->
                        service.save(
                                "",
                                "test",
                                "nickname"
                        ));

        Assertions.assertEquals(
                "email eq empty", exception.getMessage()
        );
    }

    @Test
    @DisplayName("비밀번호가 empty")
    @Transactional
    void save_pw_eq_empty() {
        Throwable exception = Assertions.assertThrows(RuntimeException.class,
                () ->
                        service.save(
                                "test@test.com",
                                "",
                                "nickname"
                        ));

        Assertions.assertEquals(
                "password eq empty", exception.getMessage()
        );
    }

    @Test
    @DisplayName("닉네임이 empty")
    @Transactional
    void save_nick_eq_empty() {
        Throwable exception = Assertions.assertThrows(RuntimeException.class,
                () ->
                        service.save(
                                "test@test.com",
                                "test",
                                ""
                        ));

        Assertions.assertEquals(
                "nick eq empty", exception.getMessage()
        );
    }

    @Test
    @DisplayName("권한이 empty")
    @Transactional
    void save_roles_eq_empty() {
        Throwable exception = Assertions.assertThrows(RuntimeException.class,
                () ->
                        service.save(
                                "test@test.com",
                                "test",
                                "nickname"
                        ));

        Assertions.assertEquals(
                "roles eq empty", exception.getMessage()
        );
    }

    @Test
    @DisplayName("이미 존재하는 이메일")
    @Transactional
    void save_email_duple() {
        service.save(
                "test@test.com",
                "test",
                "nickname"
        );
        Throwable exception = Assertions.assertThrows(RuntimeException.class,
                () ->
                        service.save(
                                "test@test.com",
                                "test",
                                "nickname2"
                        ));

        Assertions.assertEquals(
                "exist email : test@test.com", exception.getMessage()
        );

    }

    @Test
    @DisplayName("이미 존재하는 닉네임")
    @Transactional
    void save_nick_duple() {
        service.save(
                "test@test.com",
                "test",
                "nickname"
        );
        Throwable exception = Assertions.assertThrows(RuntimeException.class,
                () ->
                        service.save(
                                "test2@test.com",
                                "test",
                                "nickname"
                        ));

        Assertions.assertEquals(
                "exist nick : nickname", exception.getMessage()
        );

    }
}