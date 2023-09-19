package com.azercell.customerservice.service;

import com.azercell.customerservice.enums.TransactionType;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {
    void createTopUpTransaction(Long customerId, Double amount);

    void createPurchaseTransaction(Long customerId, Double amount);

    void refundTransaction(Long transactionId, Double amount);
}
