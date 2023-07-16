package ru.mikhailov.employeescompany.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }

}
