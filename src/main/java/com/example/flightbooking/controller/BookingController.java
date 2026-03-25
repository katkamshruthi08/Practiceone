package com.example.flightbooking.controller;

import com.example.flightbooking.dto.BookingDTO;
import com.example.flightbooking.service.BookingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public BookingDTO createBooking(@RequestBody BookingDTO bookingDTO) {
        return bookingService.createBooking(bookingDTO);
    }

    @GetMapping("/{id}")
    public BookingDTO getBooking(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @GetMapping("/test")
    public String testKafka() {
        return "App is working!";
    }
}