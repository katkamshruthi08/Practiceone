package com.example.flightbooking.service;

import com.example.flightbooking.dto.CustomerDTO;
import com.example.flightbooking.entity.Customer;
import com.example.flightbooking.mapper.CustomerMapper;
import com.example.flightbooking.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
//package com.example.flightbooking.service;

import com.example.flightbooking.dto.CustomerDTO;
import java.util.List;

public interface CustomerService {

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long id);

    CustomerDTO registerCustomer(CustomerDTO customerDTO);
}
