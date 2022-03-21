package kr.co.aske.blog_api.blog.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LoadByDomain_BlogDto {
    private final Long id;
    private final Long owner_id;

    private final String owner_name;

    private final LocalDateTime createAt;
    private final LocalDateTime updateAt;

    @QueryProjection
    public LoadByDomain_BlogDto(Long id, Long owner_id, String owner_name, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.owner_id = owner_id;
        this.owner_name = owner_name;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}
