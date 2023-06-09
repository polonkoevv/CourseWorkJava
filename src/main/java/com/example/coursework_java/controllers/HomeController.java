package com.example.coursework_java.controllers;

import com.example.coursework_java.models.User;
import com.example.coursework_java.repositories.BookingRepository;
import com.example.coursework_java.repositories.CarRepository;
import com.example.coursework_java.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.coursework_java.services.CustomUserDetailsService;

import java.text.SimpleDateFormat;

@Controller
public class HomeController {

    public String getUser(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    CarRepository carRepository;

    @GetMapping("/")
    public String main(){
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model){
        User user = userRepository.findByUsername(getUser());
        model.addAttribute("user", user);
        return "home";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String admin(){
        return "This is Admin Page";
    }

    @GetMapping("/login")
    public String log(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/profile")
    public String logout(Model model){

        User user = userRepository.findByUsername(getUser());

        model.addAttribute("user", user);

        model.addAttribute("carrep", carRepository);

        model.addAttribute("sdf", new SimpleDateFormat("dd MMMM yyyy"));

        model.addAttribute("bookList", bookingRepository.searchAllByUserId(user.getId()));

        return "profile";
    }

    @PostMapping("/addUser")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String name,
                           @RequestParam String lastname,
                           @RequestParam String email,
                           @RequestParam String phonenumber) throws Exception {


        if(userRepository.findByUsername(username) != null) {
            throw new Exception("User already exists");
        }

        User user = new User(username, name, lastname, email, phonenumber, CustomUserDetailsService.bCryptPasswordEncoder().encode(password), "USER");

        userRepository.save(user);
        return "login";
    }


    @PostMapping("/login")
    public String login(){
        return "redirect:/profile";
    }

    @GetMapping("/catalog")
    public String catalog(Model model){

        model.addAttribute("cars", carRepository.findAll());

        return "catalog";
    }

}