package com.example.flightbooking.mapper;

import com.example.flightbooking.dto.CustomerDTO;
import com.example.flightbooking.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO toDTO(Customer customer);

    Customer toEntity(CustomerDTO customerDTO);
}
