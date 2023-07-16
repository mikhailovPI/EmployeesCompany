package ru.mikhailov.employeescompany.config;

import ru.mikhailov.employeescompany.exception.ValidationException;
import ru.mikhailov.employeescompany.model.User;

public class Validation {

    public static void validationBodyUser(User user) {
        if (user.getEmail() == null) {
            throw new ValidationException("E-mail не должен быть пустым.");
        }
        if (!user.getEmail().contains("@")) {
            throw new ValidationException("Введен некорректный e-mail.");
        }
        if (user.getLastName() == null) {
            throw new ValidationException("Имя не должен быть пустым.");
        }
        if (user.getFirstName() == null) {
            throw new ValidationException("Фамилия не должна быть пустой.");
        }
        if (user.getPatronymic() == null) {
            throw new ValidationException("Отчество не должно быть пустым.");
        }
        if (user.getPhoneNumber() == null) {
            throw new ValidationException("Номер телефона не должен быть пустым.");
        }
        if (user.getPassword() == null) {
            throw new ValidationException("Пароль не должен быть пустым.");
        }
        if (user.getBirthdate() == null) {
            throw new ValidationException("Дата рождения не должна быть пустой.");
        }
    }
}
