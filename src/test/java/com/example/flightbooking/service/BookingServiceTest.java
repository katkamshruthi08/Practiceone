package com.example.flightbooking.service;

import com.example.flightbooking.dto.BookingDTO;
import com.example.flightbooking.entity.Booking;
import com.example.flightbooking.entity.Customer;
import com.example.flightbooking.entity.Flight;
import com.example.flightbooking.mapper.BookingMapper;
import com.example.flightbooking.repository.BookingRepository;
import com.example.flightbooking.repository.CustomerRepository;
import com.example.flightbooking.repository.FlightRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private BookingMapper bookingMapper;

    @InjectMocks
    private BookingService bookingService;
    @Test
    void shouldCreateBookingSuccessfully() {

        // Arrange
        BookingDTO inputDTO = new BookingDTO(null, 1L, 1L, 2, null);

        Customer customer = new Customer();
        customer.setCustomerId(1L);

        Flight flight = new Flight();
        flight.setId(1L);

        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setFlight(flight);
        booking.setSeatsBooked(2);

        Booking savedBooking = new Booking();
        savedBooking.setBookingId(100L);
        savedBooking.setCustomer(customer);
        savedBooking.setFlight(flight);
        savedBooking.setSeatsBooked(2);

        BookingDTO outputDTO = new BookingDTO(100L, 1L, 1L, 2, null);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
        when(bookingRepository.save(any(Booking.class))).thenReturn(savedBooking);
        when(bookingMapper.toDTO(savedBooking)).thenReturn(outputDTO);

        // Act
        BookingDTO result = bookingService.createBooking(inputDTO);

        // Assert
        assertNotNull(result);
        assertEquals(100L, result.getBookingId());

        verify(bookingRepository, times(1)).save(any(Booking.class));
    }
    @Test
    void shouldReturnAllBookings() {

        Booking booking = new Booking();
        booking.setBookingId(1L);

        BookingDTO dto = new BookingDTO(1L, 1L, 1L, 2, null);

        when(bookingRepository.findAll()).thenReturn(List.of(booking));
        when(bookingMapper.toDTO(booking)).thenReturn(dto);

        List<BookingDTO> result = bookingService.getAllBookings();

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getBookingId());

        verify(bookingRepository, times(1)).findAll();
    }
}