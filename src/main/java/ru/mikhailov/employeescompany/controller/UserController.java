package ru.mikhailov.employeescompany.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping(path = ADMIN_URL + "/{userId}")
    public List<UserDto> getAllUsers(
            @PathVariable Long userId,
            @RequestParam(name = "from", defaultValue = "0") int from,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        log.info("URL: /user/admin. GetMapping/Получение всех пользователей/getAllUsers");
        return userService.getAllUsers(userId, from, size);
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

    @DeleteMapping(path = ADMIN_URL + "/{userId}/{deleteUserId}")
    public void deleteUserById(
            @PathVariable Long userId,
            @PathVariable Long deleteUserId) {
        userService.deleteUserById(userId, deleteUserId);
    }
}
