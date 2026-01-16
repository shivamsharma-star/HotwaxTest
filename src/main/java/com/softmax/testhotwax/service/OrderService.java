package com.softmax.testhotwax.service;

import com.softmax.testhotwax.dto.request.*;
import com.softmax.testhotwax.dto.response.OrderResponse;

public interface OrderService {

    OrderResponse createOrder(CreateOrderRequest request);

    OrderResponse getOrderById(Integer orderId);

    OrderResponse updateOrder(Integer orderId, UpdateOrderRequest request);

    void deleteOrder(Integer orderId);

    OrderResponse addOrderItem(Integer orderId, OrderItemRequest request);

    OrderResponse updateOrderItem(Integer orderId, Integer orderItemSeqId, UpdateOrderItemRequest request);

    void deleteOrderItem(Integer orderId, Integer orderItemSeqId);
}
