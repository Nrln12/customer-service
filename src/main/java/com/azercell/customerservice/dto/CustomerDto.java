package com.azercell.customerservice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerDto {
    private String name;
    private String surname;
    private Date birthdate;
    private String gsmNumber;
    private Double balance;
}
