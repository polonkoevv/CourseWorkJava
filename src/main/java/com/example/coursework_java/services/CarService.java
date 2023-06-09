package com.example.coursework_java.services;

import com.example.coursework_java.repositories.CarRepository;
import com.example.coursework_java.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@Component
@RequiredArgsConstructor
public class CarService {

    @Autowired
    private CarRepository carRepository;




}
