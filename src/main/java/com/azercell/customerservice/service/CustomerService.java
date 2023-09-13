package com.azercell.customerservice.service;

import com.azercell.customerservice.dto.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    void createCustomer(CustomerDto customerDto);

    CustomerDto getCustomerById(Long id);

    List<CustomerDto> getAllCustomers();
}
