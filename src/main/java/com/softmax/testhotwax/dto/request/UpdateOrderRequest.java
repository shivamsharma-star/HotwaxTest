package com.softmax.testhotwax.dto.request;

public class UpdateOrderRequest {
    private Integer shippingContactMechId;
    private Integer billingContactMechId;

    public Integer getShippingContactMechId() { return shippingContactMechId; }
    public void setShippingContactMechId(Integer shippingContactMechId) { this.shippingContactMechId = shippingContactMechId; }

    public Integer getBillingContactMechId() { return billingContactMechId; }
    public void setBillingContactMechId(Integer billingContactMechId) { this.billingContactMechId = billingContactMechId; }
}
