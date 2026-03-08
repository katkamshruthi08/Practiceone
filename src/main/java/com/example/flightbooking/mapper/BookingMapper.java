package com.example.flightbooking.mapper;

import com.example.flightbooking.dto.BookingDTO;
import com.example.flightbooking.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    @Mapping(source = "customer.customerId", target = "customerId")
    @Mapping(source = "flight.id", target = "flightId")
    @Mapping(source = "bookingId", target = "bookingId")

    BookingDTO toDTO(Booking booking);
}
