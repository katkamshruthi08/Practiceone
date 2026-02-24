package com.example.flightbooking.service;

import com.example.flightbooking.dto.FlightDTO;
import com.example.flightbooking.entity.Flight;
import com.example.flightbooking.mapper.FlightMapper;
import com.example.flightbooking.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    public FlightService(FlightRepository flightRepository,
                         FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    // 🔥 Create Flight
    public FlightDTO createFlight(FlightDTO flightDTO) {

        Flight flight = flightMapper.toEntity(flightDTO);

        Flight savedFlight = flightRepository.save(flight);

        return flightMapper.toDTO(savedFlight);
    }

    // 🔥 Get All Flights
    public List<FlightDTO> getAllFlights() {

        return flightRepository.findAll()
                .stream()
                .map(flightMapper::toDTO)
                .toList();
    }
}