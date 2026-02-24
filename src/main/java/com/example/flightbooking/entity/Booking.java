package com.example.flightbooking.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    private int seatsBooked;

    private LocalDateTime bookingTime;

    public Booking() {
    }

    public Booking(Customer customer, Flight flight, int seatsBooked) {
        this.customer = customer;
        this.flight = flight;
        this.seatsBooked = seatsBooked;
    }

    // 🔥 ADD THIS METHOD HERE
    @PrePersist
    public void setBookingTime() {
        this.bookingTime = LocalDateTime.now();
    }

    public Long getBookingId() {
        return bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public void setSeatsBooked(int seatsBooked) {
        this.seatsBooked = seatsBooked;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
    public LocalDateTime getBookingTime() {
        return bookingTime;
    }
}