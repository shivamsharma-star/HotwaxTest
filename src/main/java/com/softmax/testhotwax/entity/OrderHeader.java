package com.softmax.testhotwax.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "order_header")
public class OrderHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @Column(nullable = false)
    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "shipping_contact_mech_id", nullable = false)
    private ContactMech shippingContactMech;

    @ManyToOne
    @JoinColumn(name = "billing_contact_mech_id", nullable = false)
    private ContactMech billingContactMech;

    public Integer getOrderId() { return orderId; }
    public void setOrderId(Integer orderId) { this.orderId = orderId; }

    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public ContactMech getShippingContactMech() { return shippingContactMech; }
    public void setShippingContactMech(ContactMech shippingContactMech) { this.shippingContactMech = shippingContactMech; }

    public ContactMech getBillingContactMech() { return billingContactMech; }
    public void setBillingContactMech(ContactMech billingContactMech) { this.billingContactMech = billingContactMech; }
}
