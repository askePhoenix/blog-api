package kr.co.aske.blog_api.user.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import kr.co.aske.blog_api.user.domain.UserInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
public class UserDto {
    private Long id;
    private String nickName;
    private String email;

    public UserDto(UserInfo info) {
        this.id = info.getId() == null ? -1L : info.getId();
        this.nickName = info.getNickName() == null ? "anonymous" : info.getNickName();
        this.email = info.getEmail() == null ? "anonymous@none.none" : info.getEmail();
    }
}
