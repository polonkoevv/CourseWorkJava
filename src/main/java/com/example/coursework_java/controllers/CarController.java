package com.example.coursework_java.controllers;


import com.example.coursework_java.models.Car;
import com.example.coursework_java.repositories.CarRepository;
import com.example.coursework_java.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class CarController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private CarRepository carRepository;


//    @GetMapping("/img/{name}")
//    public ResponseEntity<?> getImageByName(@PathVariable("name") String name){
//        byte[] image = imageDataService.getImage(name);
//
//        return ResponseEntity.status(HttpStatus.OK)
//                .contentType(MediaType.valueOf("image/png"))
//                .body(image);
//    }




    @PostMapping("/admin/cars/add")
    public String addCar(@RequestParam String company,
                         @RequestParam String name,
                         @RequestParam String type,
                         @RequestParam double price,
                         @RequestParam("imagename") MultipartFile file) throws IOException {

        File uploadDir = new File(uploadPath);

        if (!uploadDir.exists()){
            uploadDir.mkdir();
        }

        String uuidFile = UUID.randomUUID().toString();
        String filename = uuidFile + file.getOriginalFilename();

        file.transferTo(new File(uploadPath + "/" + filename));

        carRepository.save(new Car(company,name,type,price, filename));

        return "redirect:/admin/cars";
    }

    @GetMapping("/carsList")
    @ResponseBody
    public List<Car> carsList(){
        List<Car> cars = carRepository.findAll();
        return cars;
    }

    @GetMapping("/admin/cars")
    public String cars(Model model){
        List<Car> cars = carRepository.findAll();
        model.addAttribute("carList",cars);
        return "adminCar";
    }

    @PostMapping("/admin/cars/delete")
    public String deleteUser(@RequestParam long id){
        carRepository.delete(carRepository.getOne(id));
        return "redirect:/admin/cars";
    }


}
