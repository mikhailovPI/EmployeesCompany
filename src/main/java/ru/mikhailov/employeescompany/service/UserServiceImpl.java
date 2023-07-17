package ru.mikhailov.employeescompany.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mikhailov.employeescompany.config.PageRequestOverride;
import ru.mikhailov.employeescompany.dto.UserDto;
import ru.mikhailov.employeescompany.exception.ConflictingRequestException;
import ru.mikhailov.employeescompany.exception.NotFoundException;
import ru.mikhailov.employeescompany.mapper.UserMapper;
import ru.mikhailov.employeescompany.model.Role;
import ru.mikhailov.employeescompany.model.User;
import ru.mikhailov.employeescompany.model.UserRole;
import ru.mikhailov.employeescompany.repository.RoleRepository;
import ru.mikhailov.employeescompany.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.mikhailov.employeescompany.config.Validation.validationBodyUser;
import static ru.mikhailov.employeescompany.mapper.UserMapper.toUser;
import static ru.mikhailov.employeescompany.mapper.UserMapper.toUserDto;


@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public List<UserDto> getAllUsers(Long userId, int from, int size) {
        User user = validationUser(userId);
        adminRole(user);
        PageRequestOverride pageRequest = PageRequestOverride.of(from, size);
        return userRepository.findAll(pageRequest)
                .stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = validationUser(userId);
        userRole(user);
        return toUserDto(user);
    }

    @Override
    @Transactional
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
        User user = validationUser(userDto.getId());
        adminRole(user);
        if (!user.getLastName().equals(userDto.getLastName())) {
            user.setLastName(userDto.getLastName());
        }
        if (!user.getFirstName().equals(userDto.getFirstName())) {
            user.setFirstName(userDto.getFirstName());
        }
        if (!user.getPatronymic().equals(userDto.getPatronymic())) {
            user.setPatronymic(userDto.getPatronymic());
        }
        if (!user.getBirthdate().equals(userDto.getBirthdate())) {
            user.setBirthdate(userDto.getBirthdate());
        }
        if (!user.getEmail().equals(userDto.getEmail())) {
            user.setEmail(userDto.getEmail());
        }
        if (!user.getPassword().equals(userDto.getPassword())) {
            user.setPassword(userDto.getPassword());
        }
        if (!user.getPhoneNumber().equals(userDto.getPhoneNumber())) {
            user.setPhoneNumber(userDto.getPhoneNumber());
        }
        User userSave = userRepository.save(user);
        return toUserDto(userSave);
    }


    @Override
    public void deleteUserById(Long userId, Long deleteUserId) {
        User user = validationUser(userId);
        adminRole(user);
        userRepository.deleteById(deleteUserId);
    }

    private User validationUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Пользователь %s не существует!", userId)));
    }

    private static void adminRole(User user) {
        user.getUserRole()
                .stream()
                .filter(role -> role.getName().equals(String.valueOf(UserRole.USER)))
                .forEachOrdered(role -> {
                    throw new ConflictingRequestException(
                            String.format("Пользователь не может выполнить данное действие, т.к. его роль %s",
                                    UserRole.ADMIN));
                });
    }

    private static void userRole(User user) {
        user.getUserRole()
                .stream()
                .filter(role -> role.getName().equals(String.valueOf(UserRole.ADMIN)))
                .forEachOrdered(role -> {
                    throw new ConflictingRequestException(
                            String.format("Пользователь не может выполнить данное действие, т.к. его роль %s",
                                    UserRole.USER));
                });
    }

}
