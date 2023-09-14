package com.azercell.customerservice.controller;

import com.azercell.customerservice.enums.TransactionType;
import com.azercell.customerservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    @PostMapping("/top-up/{id}")
    public ResponseEntity<?> createTopUpTransaction(@PathVariable(name = "id") Long customerId, @RequestParam Double amount){
        transactionService.createTopUpTransaction(customerId,amount);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PostMapping("/purchase/{id}")
    public ResponseEntity<?> createPurchaseTransaction(@PathVariable(name = "id") Long customerId, @RequestParam Double amount){
        transactionService.createPurchaseTransaction(customerId,amount);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
    @PutMapping("/refund/{id}")
    public ResponseEntity<?> refundTransaction(@PathVariable(name = "id") Long transactionId, @RequestParam Double amount){
        transactionService.refundTransaction(transactionId,amount);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
