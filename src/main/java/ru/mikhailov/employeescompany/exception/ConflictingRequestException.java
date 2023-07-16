package ru.mikhailov.employeescompany.exception;

public class ConflictingRequestException extends RuntimeException {
    public ConflictingRequestException(String message) {
        super(message);
    }
}
