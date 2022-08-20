package ru.fesco.test.sviridov.error_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e) {
        InstructionsError err = new InstructionsError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleInvalidControlSumException(InvalidControlSumException e) {
        InstructionsError err = new InstructionsError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleInvalidDataException(InvalidDataException e) {
        InstructionsError err = new InstructionsError(HttpStatus.BAD_REQUEST.value(), e.getMessages());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
