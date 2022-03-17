package kr.co.aske.blog_api.blog.service;

import kr.co.aske.blog_api.blog.domain.BlogInfo;
import kr.co.aske.blog_api.blog.dto.response.LoadByDomain_BlogDto;
import kr.co.aske.blog_api.blog.dto.response.LoadById_BlogDto;
import kr.co.aske.blog_api.blog.repository.BlogRepository;
import kr.co.aske.blog_api.blog.repository.BlogRepositoryDsl;
import kr.co.aske.blog_api.user.domain.UserInfo;
import kr.co.aske.blog_api.util.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository repository;
    private final BlogRepositoryDsl repositoryDsl;

    /*
     * CRUD
     */

    // save
    public ResponseDto save(
            Long userId, String domain
    ) {
        // valid : duple domain
        if (repositoryDsl.existByDomain(domain))
            throw new NullPointerException("duple domain : " + domain);

        // valid : empty domain
        if( !StringUtils.hasText(domain) )
            throw new NullPointerException("empty domain");

        // valid : userId is null
        if( null == userId ) throw new NullPointerException("userId is null");

        // valid : already have blog
        if( repositoryDsl.existByOwner(userId) )
            throw new NullPointerException("already have blog id : " + repositoryDsl.firstExistByOwner(userId));

        Long id = repository.save(
                BlogInfo.builder()
                        .owner(UserInfo.builder().id(userId).build())
                        .domain(domain)
                        .isEnable(true)
                        .build()
        ).getId();

        return new ResponseDto(id);
    }

    // load by domain
    public LoadByDomain_BlogDto loadByDomain(
            String domain
    ) {

        return new LoadByDomain_BlogDto();
    }

    // load by id
    public LoadById_BlogDto loadById(
            Long id
    ) {

        return new LoadById_BlogDto();
    }

    // put domain
    public ResponseDto putDomain(
            Long id, String domain
    ) {

        return new ResponseDto(-1L);
    }

    // delete
    public ResponseDto delete(
            Long id, Long userId
    ) {

        return new ResponseDto(-1L);
    }
}
