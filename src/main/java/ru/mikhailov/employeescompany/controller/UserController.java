package ru.mikhailov.employeescompany.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mikhailov.employeescompany.dto.UserDto;
import ru.mikhailov.employeescompany.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    public static final String ADMIN_URL = "/users/admin";
    public static final String USER_URL = "/users/user";

    private final UserService userService;

//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    @GetMapping(path = ADMIN_URL)
    public List<UserDto> getAllUsers() {
        log.info("URL: /user/admin. GetMapping/Получение всех пользователей/getAllUsers");
        return userService.getAllUsers();
    }

    @GetMapping(path = USER_URL + "/{userId}")
    public UserDto getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping(path = ADMIN_URL)
    public UserDto createUser(@RequestBody UserDto userDto) {
        log.info("URL: /user/admin. PostMapping/Создание пользователя/createUser");
        return userService.createUser(userDto);
    }

    @PatchMapping(path = ADMIN_URL)
    public UserDto updateUser(@RequestBody UserDto userDto) {
        log.info("URL: /user/admin. PatchMapping/Обновление пользователя/updateUser");
        return userService.updateUser(userDto);
    }

    @DeleteMapping(path = ADMIN_URL + "/{userId}")
    public void deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
    }
}
