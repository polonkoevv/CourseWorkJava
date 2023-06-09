package com.example.coursework_java.repositories;

import com.example.coursework_java.models.Car;
import com.example.coursework_java.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    Car findCarById(long id);
}
