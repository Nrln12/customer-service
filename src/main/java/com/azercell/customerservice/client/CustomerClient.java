package com.azercell.customerservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="customers", url="localhost:8081/api/v1/customers")
@Service
public interface CustomerClient {
    @GetMapping("/{id}")
    ResponseEntity<?> getCustomer(@PathVariable Long id);
    @PutMapping("/top-up/{id}")
    ResponseEntity<?> topUpCustomerBalance(@PathVariable Long id, @RequestParam Double amount);

    @PutMapping("/purchase/{id}")
    ResponseEntity<?> purchaseCustomerBalance(@PathVariable Long id, @RequestParam Double amount);
}
