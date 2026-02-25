package com.example.flightbooking.service;

import com.example.flightbooking.dto.FlightDTO;
import com.example.flightbooking.entity.Flight;
import com.example.flightbooking.mapper.FlightMapper;
import com.example.flightbooking.repository.FlightRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

    @ExtendWith(MockitoExtension.class)
    class FlightServiceTest {

        @Mock
        private FlightRepository flightRepository;

        @Mock
        private FlightMapper flightMapper;

        @InjectMocks
        private FlightService flightService;

        @Test
        void shouldGetFlightById() {

            // Arrange
            Flight flight = new Flight();
            flight.setId(1L);

            FlightDTO dto = new FlightDTO(1L, "Indigo", 180);

            when(flightRepository.findById(1L))
                    .thenReturn(Optional.of(flight));

            when(flightMapper.toDTO(flight))
                    .thenReturn(dto);

            // Act
            FlightDTO result = flightService.getFlightById(1L);

            // Assert
            assertNotNull(result);
            assertEquals("Indigo", result.getAirline());
            assertEquals(180, result.getTotalSeats());
        }

        @Test
        void shouldThrowExceptionWhenFlightNotFound() {

            when(flightRepository.findById(1L))
                    .thenReturn(Optional.empty());

            assertThrows(RuntimeException.class,
                    () -> flightService.getFlightById(1L));
        }
    }