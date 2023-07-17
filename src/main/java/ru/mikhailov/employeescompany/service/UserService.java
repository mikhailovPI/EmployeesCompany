package ru.mikhailov.employeescompany.service;

import ru.mikhailov.employeescompany.dto.UserDto;
import ru.mikhailov.employeescompany.exception.ConflictingRequestException;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers(Long userId, int from, int size);

    UserDto getUserById(Long userId);

    UserDto createUser(UserDto userDto) throws ConflictingRequestException;

    UserDto updateUser(UserDto userDto);

    void deleteUserById(Long userId, Long deleteUserId);
}

