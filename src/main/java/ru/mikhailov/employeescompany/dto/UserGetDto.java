package ru.mikhailov.employeescompany.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGetDto {

    Long id;

    String lastName;

    String firstName;

    String patronymic;

    String birthdate;

    String email;

    String phoneNumber;
}
