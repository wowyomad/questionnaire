package org.wowyomad.questionaire.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.wowyomad.questionaire.utils.exceptions.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({
            AnswerInvalidException.class,
            OptionInvalidException.class,
            QuestionInvalidException.class,
            SubmissionInvalidException.class
    })
    public ResponseEntity<String> handleBadRequestException(RuntimeException e) {
        logger.error("Bad request exception occurred: {}", e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            QuestionNotFoundException.class,
            SubmissionNotFoundException.class,
            UserNotFoundException.class,
            UsernameNotFoundException.class
    })
    public ResponseEntity<String> handleNotFoundException(RuntimeException e) {
        logger.error("Not found exception occurred: {}", e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserCredentialsException.class)
    public ResponseEntity<String> handleUserCredentialsException(UserCredentialsException e) {
        logger.error("User credentials exception occurred: {}", e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserLoginAlreadyInUseException.class)
    public ResponseEntity<String> handleUserLoginAlreadyInUseException(UserLoginAlreadyInUseException e) {
        logger.error("User login conflict exception occured: {}", e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({
            AnswerException.class,
            OptionException.class,
            QuestionException.class,
            SubmissionException.class,
            UserException.class
    })
    public ResponseEntity<String> handleApplicationSpecificException(ApplicationSpecificException e) {
        logger.error("Application specific exception occurred: {}", e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        logger.error("Validation exception occurred: {}", ex.getMessage(), ex);
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e) {
        logger.error("Method argument type mismatch exception occurred: {}", e.getMessage(), e);
        String errorMessage = String.format("Invalid format for parameter '%s'", e.getName());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) {
        logger.error("General exception occurred: {}", e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
