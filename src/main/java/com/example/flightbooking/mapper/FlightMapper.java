package com.example.flightbooking.mapper;

import com.example.flightbooking.dto.FlightDTO;
import com.example.flightbooking.entity.Flight;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlightMapper {

    FlightDTO toDTO(Flight flight);

    Flight toEntity(FlightDTO flightDTO);
}