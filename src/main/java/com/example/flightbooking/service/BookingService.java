package com.example.flightbooking.service;

import com.example.flightbooking.dto.BookingDTO;
import com.example.flightbooking.entity.Booking;
import com.example.flightbooking.entity.Customer;
import com.example.flightbooking.mapper.BookingMapper;
import com.example.flightbooking.entity.Flight;
import com.example.flightbooking.repository.BookingRepository;
import com.example.flightbooking.repository.CustomerRepository;
import com.example.flightbooking.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final CustomerRepository customerRepository;
    private final FlightRepository flightRepository;

    public BookingService(BookingRepository bookingRepository,
                          CustomerRepository customerRepository,
                          FlightRepository flightRepository,
                          BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
        this.flightRepository = flightRepository;
        this.bookingMapper = bookingMapper;

    }

    public BookingDTO createBooking(BookingDTO bookingDTO) {

        Optional<Customer> customerOptional =
                customerRepository.findById(bookingDTO.getCustomerId());

        if (customerOptional.isEmpty()) {
            throw new RuntimeException("Customer not found");
        }

        Optional<Flight> flightOptional =
                flightRepository.findById(bookingDTO.getFlightId());

        if (flightOptional.isEmpty()) {
            throw new RuntimeException("Flight not found");
        }

        Booking booking = new Booking();
        booking.setCustomer(customerOptional.get());
        booking.setFlight(flightOptional.get());
        booking.setSeatsBooked(bookingDTO.getSeatsBooked());

        Booking savedBooking = bookingRepository.save(booking);

        // 🔥 USE MAPSTRUCT HERE
        return bookingMapper.toDTO(savedBooking);
    }
    // 🔥 NEW METHOD ADDED HERE

    public List<BookingDTO> getAllBookings() {

        return bookingRepository.findAll()
                .stream()
                .map(bookingMapper::toDTO)
                .toList();
    }
   // public List<BookingDTO> getAllBookings() {

       // return bookingMapper.toDTO(savedBooking);
        //return bookingRepository.findAll()
                //.stream()
              //  .map(booking -> new BookingDTO(
                  //      booking.getBookingId(),
                    //    booking.getCustomer().getCustomerId(),
                      //  booking.getFlight().getId(),
                        //booking.getSeatsBooked(),
                        //booking.getBookingTime()
                //))
               // .toList();
    }