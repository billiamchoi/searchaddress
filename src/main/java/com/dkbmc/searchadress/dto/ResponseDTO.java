package com.dkbmc.searchadress.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = false)
@Getter
public class ResponseDTO {
    private Object result;
    private String error_code;
    private String error_msg;
    private String return_msg;

    @Builder
    public ResponseDTO(Object result, String error_code, String error_msg, String return_msg) {
        this.result = result;
        this.error_code = error_code;
        this.error_msg = error_msg;
        this.return_msg = return_msg;
    }
}
