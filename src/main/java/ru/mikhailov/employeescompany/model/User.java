package ru.mikhailov.employeescompany.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = User.TABLE_USER, schema = User.SCHEMA_TABLE)
public class User {

    public static final String SCHEMA_TABLE = "public";
    public static final String TABLE_USER = "user";

    public static final String TABLE_USER_ROLES = "user_roles";
    public static final String USER_ID = "user_id";
    public static final String USER_LAST_NAME = "user_last_name";
    public static final String USER_FIRST_NAME = "user_first_name";
    public static final String USER_PATRONYMIC = "user_patronymic";
    public static final String USER_BIRTHDATE = "user_birthdate";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";
    public static final String USER_PHONE_NUMBER = "phone_number";
    public static final String ROLE_ID = "role_id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = USER_ID)
    Long id;

    @Column(name = USER_LAST_NAME)
    String lastName;

    @Column(name = USER_FIRST_NAME)
    String firstName;

    @Column(name = USER_PATRONYMIC)
    String patronymic;

    //посмотреть аннотацию для dateTime
    @Column(name = USER_BIRTHDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDate birthdate;

    @Column(name = USER_EMAIL)
    String email;

    @Column(name = USER_PASSWORD)
    String password;

    @Column(name = USER_PHONE_NUMBER)
    String phoneNumber;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = TABLE_USER_ROLES,
            joinColumns = @JoinColumn(name = USER_ID),
            inverseJoinColumns = @JoinColumn(name = ROLE_ID))
    private Set<Role> userRole = new HashSet<>();
}



