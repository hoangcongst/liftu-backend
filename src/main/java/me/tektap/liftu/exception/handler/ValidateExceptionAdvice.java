package me.tektap.liftu.exception.handler;

import me.tektap.liftu.Response.BaseResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class ValidateExceptionAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleValidateException(ConstraintViolationException exc) {
        BaseResponse response = new BaseResponse(BaseResponse.FAILED);
        response.setMessage(exc.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleValidateException(DataIntegrityViolationException exc) {
        BaseResponse response = new BaseResponse(BaseResponse.FAILED);
        response.setMessage(exc.getCause().getCause().getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}