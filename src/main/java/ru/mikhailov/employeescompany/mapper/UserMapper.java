package ru.mikhailov.employeescompany.mapper;

import ru.mikhailov.employeescompany.dto.UserDto;
import ru.mikhailov.employeescompany.dto.UserGetDto;
import ru.mikhailov.employeescompany.model.User;

public class UserMapper {

    public static User toUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getLastName(),
                userDto.getFirstName(),
                userDto.getPatronymic(),
                userDto.getBirthdate(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getPhoneNumber(),
                userDto.getUserRole()
        );
    }

    public static UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getLastName(),
                user.getFirstName(),
                user.getPatronymic(),
                user.getBirthdate(),
                user.getEmail(),
                user.getPassword(),
                user.getPhoneNumber(),
                user.getUserRole()
        );
    }

    public static UserGetDto toUserGetDto (User user) {
        return new UserGetDto(
                user.getId(),
                user.getLastName(),
                user.getFirstName(),
                user.getPatronymic(),
                user.getBirthdate(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getUserRole()
        );
    }
}
