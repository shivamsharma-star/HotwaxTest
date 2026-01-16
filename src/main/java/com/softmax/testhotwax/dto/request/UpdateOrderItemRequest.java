package com.softmax.testhotwax.dto.request;

public class UpdateOrderItemRequest {
    private Integer quantity;
    private String status;

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
