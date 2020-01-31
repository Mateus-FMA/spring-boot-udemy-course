package br.com.mateusfma.springboot.app.ws.exceptions;

import br.com.mateusfma.springboot.app.ws.ui.model.response.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorResponse> handleAnyException(Exception e, WebRequest request) {
        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(new Date());
        response.setMessage(e.getLocalizedMessage() == null ? e.toString() : e.getLocalizedMessage());

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {NullPointerException.class, UserServiceException.class})
    public ResponseEntity<ErrorResponse> handleSpecificExceptions(Exception e, WebRequest request) {
        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(new Date());
        response.setMessage(e.getLocalizedMessage() == null ? e.toString() : e.getLocalizedMessage());

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
