package kr.co.aske.blog_api.user.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserDto {
    private Long id;

    @QueryProjection
    public UserDto(Long id) {
        this.id = id;
    }
}
