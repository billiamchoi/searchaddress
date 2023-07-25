package com.dkbmc.searchaddress.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponseDTO {
    // 시간
    // 에러 코드
    // 에러 메세지

    private final LocalDateTime time = LocalDateTime.now();
    private String error_code;
    private String error_msg;

    @Builder
    public ErrorResponseDTO(String error_code, String error_msg) {
        this.error_code = error_code;
        this.error_msg = error_msg;
    }
}
