package com.example.flightbooking.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightName;
    private String source;
    private String destination;
    private double price;

    public Flight() {
    }

    public Flight(String flightName, String source, String destination, double price) {
        this.flightName = flightName;
        this.source = source;
        this.destination = destination;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setId(Long id) {
        this.id=id;

    }
}