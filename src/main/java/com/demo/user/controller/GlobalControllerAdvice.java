package com.demo.user.controller;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.demo.user.exception.ClienteNotFoundException;
import com.demo.user.exception.DuplicateResourceException;
import com.demo.user.exception.ErrorCatalog;
import com.demo.user.exception.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalControllerAdvice {

   @ResponseStatus(HttpStatus.NOT_FOUND)
   @ExceptionHandler(ClienteNotFoundException.class)
   public ResponseEntity<ErrorResponse> handleCuentaNotFound(ClienteNotFoundException e, HttpServletRequest request, HttpServletResponse response) {
      ErrorResponse errorResponse = ErrorResponse
            .builder()
            .path(request.getRequestURI())
            .status(HttpStatus.NOT_FOUND.value())
            .message(ErrorCatalog.CLIENT_NOT_FOUND.getMessage())
            .timestamp(LocalDateTime.now())
            .build();

      return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
   }

   @ResponseStatus(HttpStatus.BAD_REQUEST)
   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
      BindingResult result = ex.getBindingResult();
      return ErrorResponse
            .builder()
            .code(ErrorCatalog.INVALID_CLIENT.getCode())
            .message(ErrorCatalog.INVALID_CLIENT.getMessage())
            .details(result.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()))
            .timestamp(LocalDateTime.now())
            .build();
   }

   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   @ExceptionHandler(Exception.class)
   public ErrorResponse handleGenericError(Exception ex) {
      return ErrorResponse
            .builder()
            .code(ErrorCatalog.GENERIC_ERROR.getCode())
            .message(ErrorCatalog.GENERIC_ERROR.getMessage())
            .details(Collections.singletonList(ex.getMessage()))
            .timestamp(LocalDateTime.now())
            .build();
   }

   @ResponseStatus(HttpStatus.CONFLICT)
   @ExceptionHandler(DuplicateResourceException.class)
   public ErrorResponse handleGenericError(HttpServletRequest request) {
      return ErrorResponse
            .builder()
            .path(request.getRequestURI())
            .status(HttpStatus.CONFLICT.value())
            .message(ErrorCatalog.CLIENT_CREATION_ERROR.getMessage())
            .timestamp(LocalDateTime.now())
            .build();
   }

}
