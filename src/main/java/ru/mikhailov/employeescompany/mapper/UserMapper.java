package ru.mikhailov.employeescompany.mapper;

import ru.mikhailov.employeescompany.dto.UserDto;
import ru.mikhailov.employeescompany.model.User;

public class UserMapper {

    public static User toUser(UserDto userDto) {
        return new User();
    }

    public static UserDto toUserDto(User user) {
        return new UserDto();
    }

}
