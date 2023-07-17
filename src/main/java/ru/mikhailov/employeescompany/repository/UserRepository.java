package ru.mikhailov.employeescompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.mikhailov.employeescompany.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("select u.email from User u")
    List<String> findByNameOrderByEmail();

    @Query("SELECT u FROM User u WHERE LOWER(u.email) LIKE CONCAT('%', LOWER(:namePart),'%')")
    User findOrdersByUserEmailPart(@Param("namePart") String namePart);

}
