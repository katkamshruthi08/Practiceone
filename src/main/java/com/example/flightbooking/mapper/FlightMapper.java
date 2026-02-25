package com.example.flightbooking.mapper;

import com.example.flightbooking.dto.FlightDTO;
import com.example.flightbooking.entity.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface FlightMapper {

    @Mapping(source="id", target="id")
    FlightDTO toDTO(Flight flight);

    @Mapping(source="id", target="id")
    Flight toEntity(FlightDTO flightDTO);
}