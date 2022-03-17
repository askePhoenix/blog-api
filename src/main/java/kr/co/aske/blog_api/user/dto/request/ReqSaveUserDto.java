package kr.co.aske.blog_api.user.dto.request;

import kr.co.aske.blog_api.user.domain.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ReqSaveUserDto {
    private String email;
    private String password;
    private String nickName;

    public ReqSaveUserDto(String email, String password, String nickName) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
    }
}
