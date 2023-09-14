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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private Date birthdate;
    private String gsmNumber;
    private Double balance;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "transactions_customers",
            joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id", table = "customers"),
            inverseJoinColumns = @JoinColumn(name = "transaction_id", referencedColumnName = "id", table = "transactions")
    )
    private List<Transaction> transactions;
}
