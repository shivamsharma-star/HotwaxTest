package com.softmax.testhotwax.controller;

import com.softmax.testhotwax.dto.request.*;
import com.softmax.testhotwax.dto.response.OrderResponse;
import com.softmax.testhotwax.service.OrderService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Create Order
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
        OrderResponse response = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get Order Details
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Integer orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    // Update Order (shipping/billing)
    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable Integer orderId,
                                                     @RequestBody UpdateOrderRequest request) {
        return ResponseEntity.ok(orderService.updateOrder(orderId, request));
    }

    // Delete Order
    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok("Order deleted successfully");
    }

    // Add Order Item
    @PostMapping("/{orderId}/items")
    public ResponseEntity<OrderResponse> addOrderItem(@PathVariable Integer orderId,
                                                      @RequestBody OrderItemRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.addOrderItem(orderId, request));
    }

    // Update Order Item
    @PutMapping("/{orderId}/items/{orderItemSeqId}")
    public ResponseEntity<OrderResponse> updateOrderItem(@PathVariable Integer orderId,
                                                         @PathVariable Integer orderItemSeqId,
                                                         @RequestBody UpdateOrderItemRequest request) {
        return ResponseEntity.ok(orderService.updateOrderItem(orderId, orderItemSeqId, request));
    }

    // Delete Order Item
    @DeleteMapping("/{orderId}/items/{orderItemSeqId}")
    public ResponseEntity<String> deleteOrderItem(@PathVariable Integer orderId,
                                                  @PathVariable Integer orderItemSeqId) {
        orderService.deleteOrderItem(orderId, orderItemSeqId);
        return ResponseEntity.ok("Order item deleted successfully");
    }
}
