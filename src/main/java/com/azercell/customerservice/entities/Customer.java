package com.azercell.customerservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private Date birthdate;
    private String gsmNumber;
    private Double balance;
    @OneToMany(mappedBy="customer",fetch = FetchType.EAGER)
    private List<Transaction> transactions;
}
