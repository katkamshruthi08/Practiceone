package com.example.flightbooking.service;

import com.example.flightbooking.dto.CustomerDTO;
import com.example.flightbooking.entity.Customer;
import com.example.flightbooking.mapper.CustomerMapper;
import com.example.flightbooking.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void shouldCreateCustomerSuccessfully() {

        // Arrange
        CustomerDTO dto = new CustomerDTO(null, "John", "john@gmail.com");

        Customer customer = new Customer();
        customer.setName("John");
        customer.setEmail("john@gmail.com");

        Customer savedCustomer = new Customer();
        savedCustomer.setCustomerId(1L);
        savedCustomer.setName("John");
        savedCustomer.setEmail("john@gmail.com");

        CustomerDTO savedDTO = new CustomerDTO(1L, "John", "john@gmail.com");

        when(customerMapper.toEntity(dto)).thenReturn(customer);

        // IMPORTANT: repository must return savedCustomer
        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        // IMPORTANT: mapper must convert savedCustomer
        when(customerMapper.toDTO(savedCustomer)).thenReturn(savedDTO);

        // Act
        CustomerDTO result = customerService.createCustomer(dto);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getCustomerId());
        assertEquals("John", result.getName());
        assertEquals("john@gmail.com", result.getEmail());
    }

    @Test
    void shouldGetCustomerById() {

        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setName("John");
        customer.setEmail("john@gmail.com");

        CustomerDTO dto = new CustomerDTO(1L, "John", "john@gmail.com");

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerMapper.toDTO(customer)).thenReturn(dto);

        CustomerDTO result = customerService.getCustomerById(1L);

        assertNotNull(result);
        assertEquals("John", result.getName());
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFound() {

        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> customerService.getCustomerById(1L));
    }
}