package com.softmax.testhotwax.service;

import com.softmax.testhotwax.dto.request.CreateCustomerRequest;
import com.softmax.testhotwax.dto.request.UpdateCustomerRequest;
import com.softmax.testhotwax.dto.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CreateCustomerRequest request);
    CustomerResponse getCustomerById(Integer customerId);
    List<CustomerResponse> getAllCustomers();
    CustomerResponse updateCustomer(Integer customerId, UpdateCustomerRequest request);
    void deleteCustomer(Integer customerId);
}
