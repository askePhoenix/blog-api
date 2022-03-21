package kr.co.aske.blog_api.blog.service;

import kr.co.aske.blog_api.blog.domain.BlogInfo;
import kr.co.aske.blog_api.blog.dto.response.LoadByDomain_BlogDto;
import kr.co.aske.blog_api.blog.dto.response.LoadById_BlogDto;
import kr.co.aske.blog_api.blog.repository.BlogRepository;
import kr.co.aske.blog_api.blog.repository.BlogRepositoryDsl;
import kr.co.aske.blog_api.user.domain.UserInfo;
import kr.co.aske.blog_api.user.service.UserService;
import kr.co.aske.blog_api.util.dto.ResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlogServiceTest {

    @Autowired
    private BlogService service;

    @Autowired
    private BlogRepository repository;

    @Autowired
    private BlogRepositoryDsl repositoryDsl;

    @Autowired
    private UserService userService;


    @Test
    @Transactional
    @DisplayName("중복 도메인 저장 실패")
    void save_duple_domain() {
        // given
        Long userId = userService
                .save("blog@save.test", "test", "blogTest").getId();

        final String domain = "blog-save";

        final Long id = repository.save(
                BlogInfo.builder()
                        .owner(UserInfo.builder().id(userId).build())
                        .domain(domain)
                        .isEnable(true)
                        .build()
        ).getId();

        // when
        Throwable exception = Assertions.assertThrows(RuntimeException.class,
                () -> service.save(userId, domain));

        // then
        Assertions.assertEquals(
                "duple domain : " + domain, exception.getMessage()
        );
    }

    @Test
    @Transactional
    @DisplayName("도메인 비어있음")
    void save_empty_domain() {
        // given
        Long userId = userService
                .save("blog@save.test", "test", "blogTest").getId();

        final String domain = "";

        // when
        Throwable exception = Assertions.assertThrows(RuntimeException.class,
                () -> service.save(userId, domain));

        // then
        Assertions.assertEquals(
                "empty domain", exception.getMessage()
        );
    }

    @Test
    @Transactional
    @DisplayName("이미 본인 블로그가 있음")
    void save_already_domain() {
        // given
        Long userId = userService
                .save("blog@save.test", "test", "blogTest").getId();

        final String domain = "domain1";

        final Long id = repository.save(
                BlogInfo.builder()
                        .owner(UserInfo.builder().id(userId).build())
                        .domain(domain)
                        .isEnable(true)
                        .build()
        ).getId();

        // when
        Throwable exception = Assertions.assertThrows(RuntimeException.class,
                () -> service.save(userId, "domain2"));

        // then
        Assertions.assertEquals(
                "already have blog id : " + id, exception.getMessage()
        );
    }


    @Test
    @Transactional
    @DisplayName("사용자 ID 값이 없는 경우")
    void save_userId_idNull() {
        // given
        final String domain = "domain1";

        // when
        Throwable exception = Assertions.assertThrows(RuntimeException.class,
                () -> service.save(null, domain));

        // then
        Assertions.assertEquals(
                "userId is null", exception.getMessage()
        );
    }

    @Test
    @Transactional
    @DisplayName("사용자 ID 값이 삭제된 경우")
    void save_userId_notExist() {
        // given
        final String domain = "domain1";

        // when
        Throwable exception = Assertions.assertThrows(RuntimeException.class,
                () -> service.save(3L, domain));

        // then
        Assertions.assertEquals(
                "not exist user", exception.getMessage()
        );
    }

    @Test
    @Transactional
    @DisplayName("도메인으로 검색")
    void loadByDomain() {
        // given
        Long userId = userService
                .save("blog@save.test", "test", "blogTest").getId();
        final String domain = "domain1";

        ResponseDto successDto = service.save(userId, domain);
        // when
        LoadByDomain_BlogDto resultDto = service.loadByDomain(domain);

        // then
        Assertions.assertEquals(successDto.getMessage(), "처리 완료");
        Assertions.assertEquals("blogTest", resultDto.getOwner_name());
        Assertions.assertEquals(successDto.getId(), resultDto.getId());
        Assertions.assertEquals(userId, resultDto.getOwner_id());
    }

    @Test
    @Transactional
    @DisplayName("아이디로 검색")
    void loadById() {
        // given
        Long userId = userService
                .save("blog@save.test", "test", "blogTest").getId();
        final String domain = "domain12";
        ResponseDto successDto = service.save(userId, domain);
        // when
        LoadById_BlogDto resultDto = service.loadById(successDto.getId());

        // then
        Assertions.assertEquals(successDto.getMessage(), "처리 완료");
        Assertions.assertEquals("blogTest", resultDto.getOwner_name());
        Assertions.assertEquals("domain12", resultDto.getDomain());
        Assertions.assertEquals(userId, resultDto.getOwner_id());
    }

    @Test
    @Transactional
    void putDomain() {
        // given

        // when

        // then
        fail();
    }

    @Test
    @Transactional
    void delete() {
        // given

        // when

        // then
        fail();
    }
}