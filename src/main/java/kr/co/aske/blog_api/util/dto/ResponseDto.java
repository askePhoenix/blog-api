package kr.co.aske.blog_api.util.dto;

import lombok.Getter;

@Getter
public class ResponseDto {
    private final Long id;
    private final String message;

    public ResponseDto(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public ResponseDto(Long id) {
        this.id = id;
        this.message = "처리 완료";
    }
}
