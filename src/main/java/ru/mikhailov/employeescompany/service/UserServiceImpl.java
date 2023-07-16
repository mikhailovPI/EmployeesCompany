package ru.mikhailov.employeescompany.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mikhailov.employeescompany.dto.UserDto;
import ru.mikhailov.employeescompany.exception.ConflictingRequestException;
import ru.mikhailov.employeescompany.model.Role;
import ru.mikhailov.employeescompany.model.User;
import ru.mikhailov.employeescompany.repository.RoleRepository;
import ru.mikhailov.employeescompany.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ru.mikhailov.employeescompany.config.Validation.validationBodyUser;
import static ru.mikhailov.employeescompany.mapper.UserMapper.toUser;


@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public List<UserDto> getAllUsers() {
        return null;
    }

    @Override
    public UserDto getUserById(Long userId) {
        return null;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        validationBodyUser(toUser(userDto));
        User user = toUser(userDto);
        if (userRepository.findByNameOrderByEmail()
                .stream()
                .noneMatch(email -> email.equals(userDto.getEmail()))) {
            Set<Role> roles = new HashSet<>();
            Set<Role> roleUserDto = userDto.getUserRole();

            if (roleRepository.findAll().isEmpty()) {
                user.setUserRole(roleUserDto);
                user.setPassword(passwordEncoder.encode(userDto.getPassword()));
                user = userRepository.save(user);
                return toUserDto(user);
            }
            for (Role role : roleUserDto) {
                Role roleFromDataBase = roleRepository.findByName(role.getName());
                if (roleFromDataBase != null) {
                    roles.add(roleFromDataBase);
                } else {
                    roles.add(role);
                }
            }
            user.setUserRole(roles);
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user = userRepository.save(user);
        } else {
            throw new ConflictingRequestException(
                    String.format("Пользователь с email:  %s - уже существует!", userDto.getEmail()));
        }
        return toUserDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        return null;
    }

    @Override
    public void deleteUserById(Long userId) {

    }
}
