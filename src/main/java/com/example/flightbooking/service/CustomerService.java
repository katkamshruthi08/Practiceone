package com.example.flightbooking.service;

import com.example.flightbooking.dto.CustomerDTO;
import com.example.flightbooking.entity.Customer;
import com.example.flightbooking.mapper.CustomerMapper;
import com.example.flightbooking.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository,
                           CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    // 🔥 Create Customer using MapStruct
    public CustomerDTO registerCustomer(CustomerDTO customerDTO) {

        Customer customer = customerMapper.toEntity(customerDTO);

        Customer savedCustomer = customerRepository.save(customer);

        return customerMapper.toDTO(savedCustomer);
    }

    // 🔥 Get All Customers
    public List<CustomerDTO> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDTO)
                .toList();
    }
}