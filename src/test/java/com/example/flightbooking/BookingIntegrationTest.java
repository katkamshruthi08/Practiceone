package com.example.flightbooking;

import com.example.flightbooking.dto.BookingDTO;
import com.example.flightbooking.dto.CustomerDTO;
import com.example.flightbooking.dto.FlightDTO;
import com.example.flightbooking.service.BookingService;
import com.example.flightbooking.service.CustomerService;
import com.example.flightbooking.service.FlightService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
//import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class BookingIntegrationTest {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private FlightService flightService;

    @Test
    void shouldCreateAndFetchBooking() {

        // 1️⃣ Create Customer
        // CustomerDTO customerDTO =
        //       new CustomerDTO(null, "Shruti", "shruti@gmail.com");
        CustomerDTO customerDTO =
                new CustomerDTO(
                        null,
                        "Shruti",
                        "shruti" + System.currentTimeMillis() + "@gmail.com"
                );
        CustomerDTO savedCustomer =
                customerService.registerCustomer(customerDTO);

        // 2️⃣ Create Flight
        FlightDTO flightDTO =
                new FlightDTO(null, "Indigo", 180);

        FlightDTO savedFlight =
                flightService.createFlight(flightDTO);


        // 3️⃣ Create Booking
        //BookingDTO bookingDTO = new BookingDTO();


        BookingDTO bookingDTO = new BookingDTO(1L, 1L, 1L);
        // bookingDTO.setCustomerId(savedCustomer.getCustomerId());
        //bookingDTO.setFlightId(savedFlight.getId());

        System.out.println("Customer Id:" + savedCustomer.getCustomerId());
        System.out.println("Flight Id:" + savedFlight.getId());

        BookingDTO savedBooking =
                bookingService.createBooking(bookingDTO);

        assertNotNull(savedBooking.getBookingId());

        BookingDTO fetched =
                bookingService.getBookingById(savedBooking.getBookingId());
        System.out.println("Saved Id:" + savedBooking.getBookingId());

        assertEquals(savedBooking.getBookingId(),
                fetched.getBookingId());
    }
}