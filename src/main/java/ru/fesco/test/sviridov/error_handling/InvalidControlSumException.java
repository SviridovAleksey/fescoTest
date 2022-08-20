package ru.fesco.test.sviridov.error_handling;

public class InvalidControlSumException extends RuntimeException {
    public InvalidControlSumException(String message) {
        super(message);
    }
}
