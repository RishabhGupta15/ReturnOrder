package com.authorizationservice.authorization.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND , reason="Username/Password incorrect")
public class BadCredentialException extends Exception {

}
