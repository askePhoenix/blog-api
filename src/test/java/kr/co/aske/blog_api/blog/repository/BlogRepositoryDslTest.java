package kr.co.aske.blog_api.blog.repository;

import kr.co.aske.blog_api.blog.domain.BlogInfo;
import kr.co.aske.blog_api.user.domain.UserInfo;
import kr.co.aske.blog_api.user.domain.UserRole;
import kr.co.aske.blog_api.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlogRepositoryDslTest {
    @Autowired
    private BlogRepository repository;

    @Autowired
    private BlogRepositoryDsl repositoryDsl;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Exist")
    @Transactional
    void exist() {
        // given
        Long userId = userRepository.save(
                UserInfo.builder()
                        .email("TDD@test.test")
                        .password(new BCryptPasswordEncoder().encode("test"))
                        .nickName("TDD_USER")
                        .roles(Collections.singletonList(UserRole.USER))
                        .isEnable(true)
                        .build()
        ).getId();

        Long id = repository.save(
                BlogInfo.builder()
                        .owner(UserInfo.builder().id(userId).build())
                        .domain("tdd_blog")
                        .isEnable(true)
                        .build()
        ).getId();


        // when
        Boolean isExist = repositoryDsl.exist(id);

        // then
        Assertions.assertTrue(isExist);
    }

    @Test
    @DisplayName("Exist By Domain")
    @Transactional
    void existByDomain() {
        // given
        Long userId = userRepository.save(
                UserInfo.builder()
                        .email("TDD@test.test")
                        .password(new BCryptPasswordEncoder().encode("test"))
                        .nickName("TDD_USER")
                        .roles(Collections.singletonList(UserRole.USER))
                        .isEnable(true)
                        .build()
        ).getId();

        repository.save(
                BlogInfo.builder()
                        .owner(UserInfo.builder().id(userId).build())
                        .domain("tdd_blog")
                        .isEnable(true)
                        .build()
        );


        // when
        Boolean isExist = repositoryDsl.existByDomain("tdd_blog");

        // then
        Assertions.assertTrue(isExist);
    }

    @Test
    @DisplayName("existByOwner")
    @Transactional
    void existByOwner() {
        // given
        Long userId = userRepository.save(
                UserInfo.builder()
                        .email("TDD@test.test")
                        .password(new BCryptPasswordEncoder().encode("test"))
                        .nickName("TDD_USER")
                        .roles(Collections.singletonList(UserRole.USER))
                        .isEnable(true)
                        .build()
        ).getId();

        repository.save(
                BlogInfo.builder()
                        .owner(UserInfo.builder().id(userId).build())
                        .domain("tdd_blog")
                        .isEnable(true)
                        .build()
        );


        // when
        Boolean isExist = repositoryDsl.existByOwner(userId);

        // then
        Assertions.assertTrue(isExist);
    }


    @Test
    @DisplayName("firstExistByOwner")
    @Transactional
    void firstExistByOwner() {
        // given
        Long userId = userRepository.save(
                UserInfo.builder()
                        .email("TDD@test.test")
                        .password(new BCryptPasswordEncoder().encode("test"))
                        .nickName("TDD_USER")
                        .roles(Collections.singletonList(UserRole.USER))
                        .isEnable(true)
                        .build()
        ).getId();

        repository.save(
                BlogInfo.builder()
                        .owner(UserInfo.builder().id(userId).build())
                        .domain("tdd_blog")
                        .isEnable(true)
                        .build()
        );


        // when
        Long id = repositoryDsl.firstExistByOwner(userId);

        // then
        Assertions.assertTrue(id != -1L);
    }



}