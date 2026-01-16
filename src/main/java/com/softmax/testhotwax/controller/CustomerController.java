package com.softmax.testhotwax.controller;

import com.softmax.testhotwax.dto.request.CreateCustomerRequest;
import com.softmax.testhotwax.dto.request.UpdateCustomerRequest;
import com.softmax.testhotwax.dto.response.CustomerResponse;
import com.softmax.testhotwax.service.CustomerService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
//
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CreateCustomerRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(request));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable Integer customerId) {
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Integer customerId,
                                                           @RequestBody UpdateCustomerRequest request) {
        return ResponseEntity.ok(customerService.updateCustomer(customerId, request));
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok("Customer deleted successfully");
    }
}
