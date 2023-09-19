package com.azercell.customerservice.service.impl;

import com.azercell.customerservice.client.CustomerClient;
import com.azercell.customerservice.dto.CustomerDto;
import com.azercell.customerservice.entities.Customer;
import com.azercell.customerservice.entities.Transaction;
import com.azercell.customerservice.enums.Status;
import com.azercell.customerservice.enums.TransactionType;
import com.azercell.customerservice.exception.BadRequestException;
import com.azercell.customerservice.exception.NotFoundException;
import com.azercell.customerservice.repository.TransactionRepository;
import com.azercell.customerservice.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final CustomerClient customerClient;
    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;

    public TransactionServiceImpl(CustomerClient customerClient, TransactionRepository transactionRepository, ModelMapper modelMapper) {
        this.customerClient = customerClient;
        this.transactionRepository = transactionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createTopUpTransaction(Long customerId, Double amount) {
        ResponseEntity<?> response = customerClient.getCustomer(customerId);
        Customer findCustomer = modelMapper.map(response.getBody(), Customer.class);
        if(response != null && response.getStatusCode() == HttpStatus.OK){
            customerClient.topUpCustomerBalance(customerId, amount);
        }
        Transaction newTransaction = Transaction.builder()
                .transactionDate(LocalDateTime.now())
                .transactionType(TransactionType.TOPUP)
                .amount(amount)
                .customer(findCustomer)
                .status(Status.SUCCESS)
                .build();
        transactionRepository.save(newTransaction);
    }

    @Override
    public void createPurchaseTransaction(Long customerId, Double amount) {
        ResponseEntity<?> response = customerClient.getCustomer(customerId);
        Customer findCustomer = modelMapper.map(response.getBody(), Customer.class);
        if(response != null && response.getStatusCode() == HttpStatus.OK){
            customerClient.purchaseCustomerBalance(customerId, amount);
        }
        Transaction newTransaction = Transaction.builder()
                .transactionDate(LocalDateTime.now())
                .transactionType(TransactionType.PURCHASE)
                .amount(amount)
                .customer(findCustomer)
                .status(Status.SUCCESS)
                .build();
        transactionRepository.save(newTransaction);
    }

    @Override
    public void refundTransaction(Long transactionId, Double amount) {
        Transaction findTransaction = transactionRepository.findById(transactionId).orElseThrow(() -> new NotFoundException("Transaction has not found"));
        ResponseEntity<?> response = customerClient.getCustomer(findTransaction.getCustomer().getId());
        Customer findCustomer = modelMapper.map(response.getBody(), Customer.class);
        if(response != null && response.getStatusCode() == HttpStatus.OK){
            customerClient.topUpCustomerBalance(findTransaction.getCustomer().getId(), amount);
        }
        if(findTransaction.getAmount()<=amount){
            throw new BadRequestException("Invalid refund operation");
        }
        findCustomer.setBalance(findCustomer.getBalance()+amount);
        Transaction newTransaction = Transaction.builder()
                .transactionDate(LocalDateTime.now())
                .transactionType(TransactionType.REFUND)
                .amount(amount)
                .customer(findTransaction.getCustomer())
                .status(Status.SUCCESS)
                .build();
        transactionRepository.save(newTransaction);
    }
}
