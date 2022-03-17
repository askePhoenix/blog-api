package kr.co.aske.blog_api.blog.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.aske.blog_api.blog.domain.BlogInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static kr.co.aske.blog_api.blog.domain.QBlogInfo.blogInfo;

@RequiredArgsConstructor
@Repository
public class BlogRepositoryDsl {
    private final JPAQueryFactory queryFactory;



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
        return null != fetchOne ? fetchOne.getId() : -1L ;
    }
}
