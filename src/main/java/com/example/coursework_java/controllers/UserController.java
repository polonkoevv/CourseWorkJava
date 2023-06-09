package com.example.coursework_java.controllers;


import com.example.coursework_java.models.User;
import com.example.coursework_java.repositories.BookingRepository;
import com.example.coursework_java.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    public String getUser(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Autowired
    UserRepository userRepository;



    @PostMapping("/admin/users/delUser")
    public String delUser(@RequestParam long id){

        userRepository.delete(userRepository.getOne(id));
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users")
    public String users(Model model){

        List<User> us = userRepository.findAll();


        model.addAttribute("userList", us);

        return "adminUser";
    }

    @PostMapping("/admin/users/delete")
    public String deleteUser(@RequestParam long id){
        User u = userRepository.getOne(id);
        if (!u.equals(userRepository.findByUsername(getUser()))) {
            userRepository.deleteById(id);
        }
        return "redirect:/admin/users";
    }

    @PostMapping("/admin/users/changeRole")
    public String changeRole(@RequestParam long id){
        User u = userRepository.getOne(id);
        if (!u.equals(userRepository.findByUsername(getUser()))) {
            if (u.getRole().equals("ADMIN")) {
                u.setRole("USER");
            } else {
                u.setRole("ADMIN");
            }
            userRepository.save(u);
        }
        return "redirect:/admin/users";
    }
}
