package com.example.flightbooking.service;
import com.example.flightbooking.dto.CustomerDTO;
import com.example.flightbooking.entity.Customer;
import com.example.flightbooking.service.CustomerService;
import com.example.flightbooking.mapper.CustomerMapper;
import com.example.flightbooking.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository,
                               CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        Customer saved = customerRepository.save(customer);
        return customerMapper.toDTO(saved);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return customerMapper.toDTO(customer);
    }

    @Override
   // public CustomerDTO registerCustomer(CustomerDTO customerDTO) {

        public CustomerDTO registerCustomer(CustomerDTO dto) {

            Customer customer = customerMapper.toEntity(dto);

            Customer saved = customerRepository.save(customer);

            return customerMapper.toDTO(saved);
           // System.out.println("Inside registerCustomer");
        }}
