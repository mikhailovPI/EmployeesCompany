package ru.mikhailov.employeescompany.vaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mikhailov.employeescompany.dto.UserDto;
import ru.mikhailov.employeescompany.dto.UserGetDto;
import ru.mikhailov.employeescompany.model.User;
import ru.mikhailov.employeescompany.model.UserRole;
import ru.mikhailov.employeescompany.service.UserService;

public class MainView extends VerticalLayout {

    @Autowired
    private UserService userService;

    public MainView() {
        TextField usernameField = new TextField("Логин");
        PasswordField passwordField = new PasswordField("Пароль");
        Button loginButton = new Button("Войти");
        loginButton.addClickListener(event -> {
            User user = userService.findByEmail((usernameField.getValue()));
            if (user != null && passwordField.getValue().equals(user.getPassword())) {
                if (user.getUserRole()
                        .stream()
                        .anyMatch(role -> role.getName().equals(String.valueOf(UserRole.USER)))) {
                    // Загрузка таблицы пользователей для администратора
                    Grid<UserGetDto> userGrid = new Grid<>(UserGetDto.class);
                    userGrid.setItems(userService.getAllUsers(user.getId(), 0, 10));
                    add(userGrid);
                } else {
                    Grid<UserDto> userGrid = new Grid<>(UserDto.class);
                    userGrid.setItems(userService.getUserById(user.getId()));
                    add(userGrid);
                }
            } else {
                Notification.show("Неправильный логин или пароль", 3000, Notification.Position.TOP_CENTER);
            }
        });

        add(usernameField, passwordField, loginButton);
    }
}