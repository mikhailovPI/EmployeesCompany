package ru.mikhailov.employeescompany.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import ru.mikhailov.employeescompany.model.Role;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class UserDto {

    Long id;

    String lastName;

    String firstName;

    String patronymic;

    LocalDate birthdate;

    String email;

    String password;

    String phoneNumber;

    Set<Role> userRole = new HashSet<>();
}
