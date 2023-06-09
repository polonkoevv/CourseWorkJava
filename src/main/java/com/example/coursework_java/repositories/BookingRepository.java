package com.example.coursework_java.repositories;

import com.example.coursework_java.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> searchAllByUserId(long id);
}
