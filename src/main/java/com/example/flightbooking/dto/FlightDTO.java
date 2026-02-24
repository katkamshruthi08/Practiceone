package com.example.flightbooking.dto;

public class FlightDTO {

    private Long id;
    private String airline;
    private int totalSeats;

    public FlightDTO() {
    }

    public FlightDTO(Long id, String airline, int totalSeats) {
        this.id = id;
        this.airline = airline;
        this.totalSeats = totalSeats;
    }

    public Long getId() {
        return id;
    }

    public String getAirline() {
        return airline;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }
}