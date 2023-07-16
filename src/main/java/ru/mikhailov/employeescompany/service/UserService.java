package ru.mikhailov.employeescompany.service;

import ru.mikhailov.employeescompany.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto getUserById(Long userId);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);

    void deleteUserById(Long userId);
}

