package com.example.flightbooking.controller;

import com.example.flightbooking.dto.BookingDTO;
import com.example.flightbooking.dto.CustomerDTO;
import com.example.flightbooking.dto.FlightDTO;
import com.example.flightbooking.service.CustomerService;
import com.example.flightbooking.service.FlightService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class BookingControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private FlightService flightService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateAndFetchBooking() throws Exception {

        // 1️⃣ Create Customer (unique email)
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("John");
        customerDTO.setEmail("john" + System.currentTimeMillis() + "@example.com");

        CustomerDTO savedCustomer = customerService.createCustomer(customerDTO);
        assertNotNull(savedCustomer);
        assertNotNull(savedCustomer.getCustomerId());

        // 2️⃣ Create Flight
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setAirline("Indigo");
        flightDTO.setTotalSeats(180);
        flightDTO.setSource("Hyderabad");
        flightDTO.setDestination("Delhi");
        flightDTO.setPrice(5000.0);

        FlightDTO savedFlight = flightService.createFlight(flightDTO);
        assertNotNull(savedFlight);
        assertNotNull(savedFlight.getId());

        // 3️⃣ Create Booking JSON (DO NOT use BookingDTO(1L,1L,1L))
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setCustomerId(savedCustomer.getCustomerId());
        bookingDTO.setFlightId(savedFlight.getId());
        bookingDTO.setSeatsBooked(2);

        // 4️⃣ POST /bookings
        String bookingResponse = mockMvc.perform(post("/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookingDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookingId").exists())
                .andExpect(jsonPath("$.seatsBooked").value(2))
                .andReturn()
                .getResponse()
                .getContentAsString();

        BookingDTO savedBooking = objectMapper.readValue(bookingResponse, BookingDTO.class);
        Long bookingId = savedBooking.getBookingId();

        // 5️⃣ GET /bookings/{id}
        mockMvc.perform(get("/bookings/" + bookingId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookingId").value(bookingId))
                .andExpect(jsonPath("$.seatsBooked").value(2))
                .andExpect(jsonPath("$.customerId").value(savedCustomer.getCustomerId()))
                .andExpect(jsonPath("$.flightId").value(savedFlight.getId()));
    }
}