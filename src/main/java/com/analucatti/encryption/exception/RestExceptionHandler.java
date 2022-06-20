package com.analucatti.encryption.exception;

import com.analucatti.encryption.dto.Error;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Error> handleFeignException(Exception ex) {
        return new ResponseEntity<>(new Error(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
