package br.com.mateusfma.springboot.app.ws.exceptions;

public class UserServiceException extends Exception {

    public UserServiceException(String message) {
        super(message);
    }

}
