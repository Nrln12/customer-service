package com.azercell.customerservice.service.impl;

import com.azercell.customerservice.dto.CustomerDto;
import com.azercell.customerservice.entities.Customer;
import com.azercell.customerservice.exception.BadRequestException;
import com.azercell.customerservice.exception.NotFoundException;
import com.azercell.customerservice.repository.CustomerRepository;
import com.azercell.customerservice.service.CustomerService;
import com.azercell.customerservice.utils.ValidationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    @Override
    public void createCustomer(CustomerDto customerDto) {
        Optional<Customer> findCustomer = customerRepository.findByGsmNumber(customerDto.getGsmNumber());
        if(findCustomer.isPresent()){
            throw new BadRequestException("Customer already exists");
        }
        customerDto.setBalance(100.0);
        customerRepository.save(modelMapper.map(customerDto, Customer.class));
    }

    @Override
    @Transactional
    public void topUpCustomerBalance(Long id, Double amount) {
        Customer findCustomer = customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer doesn't exist"));
        findCustomer.setBalance(findCustomer.getBalance()+amount);
        customerRepository.save(findCustomer);
    }

    @Override
    public void purchaseFromCustomerBalance(Long id, Double amount) {
        Customer findCustomer = customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer doesn't exist"));
        if(ValidationUtils.isValidPurchase(findCustomer.getBalance(), amount))
            findCustomer.setBalance(findCustomer.getBalance()-amount);
        customerRepository.save(findCustomer);
    }

    @Override
    public void refundCustomerBalance(Long id, Double amount) {

    }
    @Override
    @Transactional
    public CustomerDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer doesn't exist"));
        return modelMapper.map(customer, CustomerDto.class);
    }
    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        return Arrays.asList(modelMapper.map(customerList, CustomerDto[].class));
    }

}
