package com.softmax.testhotwax.service.impl;

import com.softmax.testhotwax.dto.request.CreateCustomerRequest;
import com.softmax.testhotwax.dto.request.UpdateCustomerRequest;
import com.softmax.testhotwax.dto.response.CustomerResponse;
import com.softmax.testhotwax.entity.Customer;
import com.softmax.testhotwax.repository.CustomerRepository;
import com.softmax.testhotwax.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponse createCustomer(CreateCustomerRequest request) {
        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());

        Customer saved = customerRepository.save(customer);
        return new CustomerResponse(saved.getCustomerId(), saved.getFirstName(), saved.getLastName());
    }

    @Override
    public CustomerResponse getCustomerById(Integer customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found: " + customerId));

        return new CustomerResponse(customer.getCustomerId(), customer.getFirstName(), customer.getLastName());
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(c -> new CustomerResponse(c.getCustomerId(), c.getFirstName(), c.getLastName()))
                .toList();
    }

    @Override
    public CustomerResponse updateCustomer(Integer customerId, UpdateCustomerRequest request) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found: " + customerId));

        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());

        Customer updated = customerRepository.save(customer);
        return new CustomerResponse(updated.getCustomerId(), updated.getFirstName(), updated.getLastName());
    }

    @Override
    public void deleteCustomer(Integer customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found: " + customerId));
        customerRepository.delete(customer);
    }
}
