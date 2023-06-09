package com.example.coursework_java.models;

import com.example.coursework_java.services.ImageService;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;


@Entity
@Table(name="car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String company;
    private String name;
    private String type;
    private Double price;

    @Column(name = "imagedata")
    private String imageData;

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }


    public Car(String company, String name, String type, Double price, String imageData){
        this.company = company;
        this.name = name;
        this.type = type;
        this.price = price;
        this.imageData = imageData;
    }





    public Car() {

    }


    public String getFullName(){
        return company + " " + name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }




    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
