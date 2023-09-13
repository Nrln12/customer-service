package com.azercell.customerservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;
@Data
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    private Long id;
    private String name;
    private String surname;
    private Date birthdate;
    private String gsmNumber;
    private Double balance;
}
