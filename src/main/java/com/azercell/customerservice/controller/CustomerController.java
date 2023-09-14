//package com.azercell.customerservice.controller;
//
//import com.azercell.customerservice.dto.CustomerDto;
//import com.azercell.customerservice.service.CustomerService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("api/v1/customers")
//@RequiredArgsConstructor
//public class CustomerController {
//    private final CustomerService customerService;
//    @PostMapping()
//    public ResponseEntity<?> createCustomer(@RequestBody CustomerDto customerDto){
//        customerService.createCustomer(customerDto);
//        return ResponseEntity.ok(HttpStatus.CREATED);
//    }
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getCustomer(@PathVariable Long id){
//        return ResponseEntity.ok(customerService.getCustomerById(id));
//    }
//    @GetMapping
//    public ResponseEntity<?> getAllCustomers(){
//        return ResponseEntity.ok(customerService.getAllCustomers());
//    }
//}
