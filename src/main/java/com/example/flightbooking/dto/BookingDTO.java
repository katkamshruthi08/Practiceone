package com.example.flightbooking.dto;

public class BookingDTO {

    private Long bookingId;
    private Long customerId;
    private Long flightId;
    private int seatsBooked;

    // ✅ ADD THIS (VERY IMPORTANT)
    public BookingDTO() {
    }

    // existing constructor
    public BookingDTO(Long bookingId, Long customerId, Long flightId) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.flightId = flightId;
    }

    // getters
    public Long getBookingId() {
        return bookingId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Long getFlightId() {
        return flightId;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    // setters
    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public void setSeatsBooked(int seatsBooked) {
        this.seatsBooked = seatsBooked;
    }
}