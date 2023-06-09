package com.example.coursework_java.repositories;

import com.example.coursework_java.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);
    User searchUserByRole(String role);

}