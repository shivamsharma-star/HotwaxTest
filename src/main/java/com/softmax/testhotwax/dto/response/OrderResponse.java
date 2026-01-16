package com.softmax.testhotwax.dto.response;

import java.time.LocalDate;
import java.util.List;

public class OrderResponse {

    private Integer orderId;
    private LocalDate orderDate;

    private CustomerResponse customer;
    private ContactMechResponse shippingAddress;
    private ContactMechResponse billingAddress;

    private List<OrderItemResponse> items;

    public OrderResponse() {}

    public OrderResponse(Integer orderId, LocalDate orderDate, CustomerResponse customer,
                         ContactMechResponse shippingAddress, ContactMechResponse billingAddress,
                         List<OrderItemResponse> items) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customer = customer;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
        this.items = items;
    }

    public Integer getOrderId() { return orderId; }
    public void setOrderId(Integer orderId) { this.orderId = orderId; }

    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }

    public CustomerResponse getCustomer() { return customer; }
    public void setCustomer(CustomerResponse customer) { this.customer = customer; }

    public ContactMechResponse getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(ContactMechResponse shippingAddress) { this.shippingAddress = shippingAddress; }

    public ContactMechResponse getBillingAddress() { return billingAddress; }
    public void setBillingAddress(ContactMechResponse billingAddress) { this.billingAddress = billingAddress; }

    public List<OrderItemResponse> getItems() { return items; }
    public void setItems(List<OrderItemResponse> items) { this.items = items; }
}
