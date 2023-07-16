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
@Table(name = Employee.TABLE_EMPLOYEE, schema = Employee.SCHEMA_TABLE)
public class Employee {

    public static final String SCHEMA_TABLE = "public";
    public static final String TABLE_EMPLOYEE = "employees";

    public static final String TABLE_EMPLOYEE_ROLES = "employee_roles";
    public static final String EMPLOYEE_ID = "employee_id";
    public static final String EMPLOYEE_LAST_NAME = "employee_last_name";
    public static final String EMPLOYEE_FIRST_NAME = "employee_first_name";
    public static final String EMPLOYEE_PATRONYMIC = "employee_patronymic";
    public static final String EMPLOYEE_BIRTHDATE = "employee_birthdate";
    public static final String EMPLOYEE_EMAIL = "email";
    public static final String EMPLOYEE_PHONE_NUMBER = "phone_number";
    public static final String ROLE_ID = "role_id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = EMPLOYEE_ID)
    Long id;

    @Column(name = EMPLOYEE_LAST_NAME)
    String lastName;

    @Column(name = EMPLOYEE_FIRST_NAME)
    String firstName;

    @Column(name = EMPLOYEE_PATRONYMIC)
    String patronymic;

    //посмотреть аннотацию для dateTime
    @Column(name = EMPLOYEE_BIRTHDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDate birthdate;

    @Column(name = EMPLOYEE_EMAIL)
    String email;

    @Column(name = EMPLOYEE_PHONE_NUMBER)
    String phoneNumber;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = TABLE_EMPLOYEE_ROLES,
            joinColumns = @JoinColumn(name = EMPLOYEE_ID),
            inverseJoinColumns = @JoinColumn(name = ROLE_ID))
    private Set<Role> userRole = new HashSet<>();

}



