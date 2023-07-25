package com.dkbmc.searchaddress.exception;

import com.dkbmc.searchaddress.dto.ErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Object> handleNoDataFoundException(NoDataFoundException ex) {
        log.info(ex.getStackTrace().toString());
        return new ResponseEntity<>(ErrorResponseDTO.builder()
                .error_msg(ex.getMessage())
                .build(),
                HttpStatus.NOT_FOUND);
    }
}
