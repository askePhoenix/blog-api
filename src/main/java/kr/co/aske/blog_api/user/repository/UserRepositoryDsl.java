package kr.co.aske.blog_api.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.aske.blog_api.user.domain.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static kr.co.aske.blog_api.user.domain.QUserInfo.userInfo;

@RequiredArgsConstructor
@Repository
public class UserRepositoryDsl {
    private final JPAQueryFactory queryFactory;

    public Optional<UserInfo> findByEmail(String email){
        return Optional.ofNullable(queryFactory
                .selectFrom(userInfo)
                .where(
                        userInfo.isEnable.eq(true),
                        userInfo.email.eq(email)
                )
                .fetchOne());
    }

    @Transactional(readOnly = true)
    public Boolean exist(Long id) {
        UserInfo fetchOne = queryFactory
                .selectFrom(userInfo)
                .where(
                        userInfo.isEnable.eq(true),
                        userInfo.id.eq(id)
                )
                .fetchFirst();
        return null != fetchOne;
    }

    @Transactional(readOnly = true)
    public Boolean existByEmail(String email) {
        UserInfo fetchOne = queryFactory
                .selectFrom(userInfo)
                .where(
                        userInfo.isEnable.eq(true),
                        userInfo.email.eq(email)
                )
                .fetchFirst();
        return null != fetchOne;
    }

    public boolean existByNick(String nickName) {
        UserInfo fetchOne = queryFactory
                .selectFrom(userInfo)
                .where(
                        userInfo.isEnable.eq(true),
                        userInfo.nickName.eq(nickName)
                )
                .fetchFirst();
        return null != fetchOne;
    }
}
