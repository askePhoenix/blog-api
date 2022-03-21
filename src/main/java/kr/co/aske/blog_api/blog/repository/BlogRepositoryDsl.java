package kr.co.aske.blog_api.blog.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.aske.blog_api.blog.domain.BlogInfo;
import kr.co.aske.blog_api.blog.dto.response.LoadByDomain_BlogDto;
import kr.co.aske.blog_api.blog.dto.response.LoadById_BlogDto;
import kr.co.aske.blog_api.blog.dto.response.QLoadByDomain_BlogDto;
import kr.co.aske.blog_api.blog.dto.response.QLoadById_BlogDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static kr.co.aske.blog_api.blog.domain.QBlogInfo.blogInfo;

@RequiredArgsConstructor
@Repository
public class BlogRepositoryDsl {
    private final JPAQueryFactory queryFactory;


    public LoadByDomain_BlogDto findByDomain(String domain) {
        return queryFactory.select(
                        new QLoadByDomain_BlogDto(
                                blogInfo.id,
                                blogInfo.owner.id,
                                blogInfo.owner.nickName,
                                blogInfo.createAt,
                                blogInfo.updateAt
                        )
                )
                .from(blogInfo)
                .where(
                        blogInfo.isEnable.eq(true),
                        blogInfo.domain.eq(domain)
                )
                .fetchOne()
                ;

    }

    public LoadById_BlogDto findById(Long id) {
        return queryFactory.select(
                new QLoadById_BlogDto(
                        blogInfo.domain,
                        blogInfo.owner.id,
                        blogInfo.owner.nickName,
                        blogInfo.createAt,
                        blogInfo.updateAt
                )
        )
                .from(blogInfo)
                .where(
                        blogInfo.isEnable.eq(true),
                        blogInfo.id.eq(id)
                )
                .fetchOne();
    }

    @Transactional(readOnly = true)
    public Boolean exist(Long id) {
        BlogInfo fetchOne = queryFactory
                .selectFrom(blogInfo)
                .where(
                        blogInfo.isEnable.eq(true),
                        blogInfo.id.eq(id)
                )
                .fetchFirst();
        return null != fetchOne;
    }

    @Transactional(readOnly = true)
    public Boolean existByDomain(String domain) {
        BlogInfo fetchOne = queryFactory
                .selectFrom(blogInfo)
                .where(
                        blogInfo.isEnable.eq(true),
                        blogInfo.domain.eq(domain)
                )
                .fetchFirst();
        return null != fetchOne;
    }


    @Transactional(readOnly = true)
    public Boolean existByOwner(Long userId) {
        BlogInfo fetchOne = queryFactory
                .selectFrom(blogInfo)
                .where(
                        blogInfo.isEnable.eq(true),
                        blogInfo.owner.id.eq(userId)
                )
                .fetchFirst();
        return null != fetchOne;
    }

    @Transactional(readOnly = true)
    public Long firstExistByOwner(Long userId) {
        BlogInfo fetchOne = queryFactory
                .selectFrom(blogInfo)
                .where(
                        blogInfo.isEnable.eq(true),
                        blogInfo.owner.id.eq(userId)
                )
                .fetchFirst();
        return null != fetchOne ? fetchOne.getId() : -1L;
    }
}
