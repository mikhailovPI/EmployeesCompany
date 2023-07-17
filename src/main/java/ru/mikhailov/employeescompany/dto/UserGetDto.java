package ru.mikhailov.employeescompany.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mikhailov.employeescompany.model.Role;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGetDto {

    Long id;

    String lastName;

    String firstName;

    String patronymic;

    //LocalDate birthdate;
    String birthdate;

    String email;

    String phoneNumber;

    Set<Role> userRole = new HashSet<>();
}
