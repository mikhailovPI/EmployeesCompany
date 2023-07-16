package ru.mikhailov.employeescompany.exception;

public class ConflictingRequestException extends Throwable {
    public ConflictingRequestException(String message) {
        super(message);
    }
}
