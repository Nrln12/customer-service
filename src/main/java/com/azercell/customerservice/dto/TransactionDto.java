package com.azercell.customerservice.dto;

import com.azercell.customerservice.enums.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionDto {
    private LocalDateTime transactionDate;
    private Double amount;
    private Status status;
}
