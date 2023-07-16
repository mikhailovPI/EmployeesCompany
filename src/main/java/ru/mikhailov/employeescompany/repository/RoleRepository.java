package ru.mikhailov.employeescompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mikhailov.employeescompany.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
