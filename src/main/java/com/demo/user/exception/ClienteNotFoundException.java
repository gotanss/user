package com.demo.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ClienteNotFoundException extends RuntimeException {

   public ClienteNotFoundException(String message) {
      super(message);

   }
}
