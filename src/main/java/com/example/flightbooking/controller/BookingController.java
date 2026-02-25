package com.example.flightbooking.controller;

import com.example.flightbooking.dto.BookingDTO;
import com.example.flightbooking.service.BookingService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public BookingDTO createBooking(@RequestBody BookingDTO bookingDTO) {
        return bookingService.createBooking(bookingDTO);
    }

    @GetMapping("/{id}")
    public BookingDTO getBooking(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }
}