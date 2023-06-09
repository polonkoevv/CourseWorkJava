package com.example.coursework_java.controllers;


import com.example.coursework_java.models.Booking;
import com.example.coursework_java.models.Car;
import com.example.coursework_java.repositories.BookingRepository;
import com.example.coursework_java.repositories.CarRepository;
import com.example.coursework_java.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class BookingController {

    public String getUser(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CarRepository carRepository;

    @GetMapping("/booking")
    public String bookingList(@RequestParam long carId, Model model){
        model.addAttribute("carId", carId);
        model.addAttribute("userId", userRepository.findByUsername(getUser()).getId());
        return "order";
    }

    @GetMapping("/admin/booking")
    public String booking(Model model){
        List<Booking> booking = bookingRepository.findAll();
        model.addAttribute("bookList",booking);
        return "adminBooking";
    }

    @PostMapping("/admin/booking/delete")
    public String deleteBooking(@RequestParam long id){
        bookingRepository.delete(bookingRepository.getOne(id));

//        bookingRepository.searchByUserId(id).set
        return "redirect:/admin/booking";
    }

    @PostMapping("/booking/add")
    public String addBooking(@RequestParam long userId, @RequestParam long carId, @RequestParam String startDate, @RequestParam String endDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        bookingRepository.save(new Booking(
                userId,
                carId,
                sdf.parse(startDate),
                sdf.parse(endDate)
        ));

        return "redirect:/catalog";
    }



}
