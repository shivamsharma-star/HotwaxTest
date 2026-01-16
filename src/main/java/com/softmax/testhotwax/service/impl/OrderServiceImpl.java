package com.softmax.testhotwax.service.impl;

import com.softmax.testhotwax.dto.request.*;
import com.softmax.testhotwax.dto.response.*;
import com.softmax.testhotwax.entity.*;
import com.softmax.testhotwax.repository.*;
import com.softmax.testhotwax.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final CustomerRepository customerRepository;
    private final ContactMechRepository contactMechRepository;
    private final ProductRepository productRepository;
    private final OrderHeaderRepository orderHeaderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderServiceImpl(CustomerRepository customerRepository,
                            ContactMechRepository contactMechRepository,
                            ProductRepository productRepository,
                            OrderHeaderRepository orderHeaderRepository,
                            OrderItemRepository orderItemRepository) {
        this.customerRepository = customerRepository;
        this.contactMechRepository = contactMechRepository;
        this.productRepository = productRepository;
        this.orderHeaderRepository = orderHeaderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public OrderResponse createOrder(CreateOrderRequest request) {

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found: " + request.getCustomerId()));

        ContactMech shipping = contactMechRepository.findById(request.getShippingContactMechId())
                .orElseThrow(() -> new RuntimeException("Shipping contact not found: " + request.getShippingContactMechId()));

        ContactMech billing = contactMechRepository.findById(request.getBillingContactMechId())
                .orElseThrow(() -> new RuntimeException("Billing contact not found: " + request.getBillingContactMechId()));

        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setOrderDate(request.getOrderDate());
        orderHeader.setCustomer(customer);
        orderHeader.setShippingContactMech(shipping);
        orderHeader.setBillingContactMech(billing);

        OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);

        for (OrderItemRequest itemReq : request.getOrderItems()) {
            Product product = productRepository.findById(itemReq.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + itemReq.getProductId()));

            OrderItem item = new OrderItem();
            item.setOrder(savedOrder);
            item.setProduct(product);
            item.setQuantity(itemReq.getQuantity());
            item.setStatus(itemReq.getStatus());

            orderItemRepository.save(item);
        }

        return getOrderById(savedOrder.getOrderId());
    }

    @Override
    public OrderResponse getOrderById(Integer orderId) {
        OrderHeader order = orderHeaderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));

        List<OrderItem> items = orderItemRepository.findByOrder_OrderId(orderId);

        return mapToOrderResponse(order, items);
    }

    @Override
    public OrderResponse updateOrder(Integer orderId, UpdateOrderRequest request) {

        OrderHeader order = orderHeaderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));

        ContactMech shipping = contactMechRepository.findById(request.getShippingContactMechId())
                .orElseThrow(() -> new RuntimeException("Shipping contact not found: " + request.getShippingContactMechId()));

        ContactMech billing = contactMechRepository.findById(request.getBillingContactMechId())
                .orElseThrow(() -> new RuntimeException("Billing contact not found: " + request.getBillingContactMechId()));

        order.setShippingContactMech(shipping);
        order.setBillingContactMech(billing);

        orderHeaderRepository.save(order);

        return getOrderById(orderId);
    }

    @Override
    public void deleteOrder(Integer orderId) {
        OrderHeader order = orderHeaderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));
        orderHeaderRepository.delete(order);
    }

    @Override
    public OrderResponse addOrderItem(Integer orderId, OrderItemRequest request) {

        OrderHeader order = orderHeaderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found: " + request.getProductId()));

        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setProduct(product);
        item.setQuantity(request.getQuantity());
        item.setStatus(request.getStatus());

        orderItemRepository.save(item);

        return getOrderById(orderId);
    }

    @Override
    public OrderResponse updateOrderItem(Integer orderId, Integer orderItemSeqId, UpdateOrderItemRequest request) {

        OrderItem item = orderItemRepository.findById(orderItemSeqId)
                .orElseThrow(() -> new RuntimeException("Order item not found: " + orderItemSeqId));

        if (!item.getOrder().getOrderId().equals(orderId)) {
            throw new RuntimeException("Order item does not belong to orderId: " + orderId);
        }

        item.setQuantity(request.getQuantity());
        item.setStatus(request.getStatus());

        orderItemRepository.save(item);

        return getOrderById(orderId);
    }

    @Override
    public void deleteOrderItem(Integer orderId, Integer orderItemSeqId) {

        OrderItem item = orderItemRepository.findById(orderItemSeqId)
                .orElseThrow(() -> new RuntimeException("Order item not found: " + orderItemSeqId));

        if (!item.getOrder().getOrderId().equals(orderId)) {
            throw new RuntimeException("Order item does not belong to orderId: " + orderId);
        }

        orderItemRepository.delete(item);
    }

    private OrderResponse mapToOrderResponse(OrderHeader order, List<OrderItem> items) {

        Customer c = order.getCustomer();

        CustomerResponse customerResponse = new CustomerResponse(
                c.getCustomerId(),
                c.getFirstName(),
                c.getLastName()
        );

        ContactMechResponse shipping = mapContact(order.getShippingContactMech());
        ContactMechResponse billing = mapContact(order.getBillingContactMech());

        List<OrderItemResponse> itemResponses = items.stream().map(i -> new OrderItemResponse(
                i.getOrderItemSeqId(),
                i.getProduct().getProductId(),
                i.getProduct().getProductName(),
                i.getQuantity(),
                i.getStatus()
        )).toList();

        return new OrderResponse(
                order.getOrderId(),
                order.getOrderDate(),
                customerResponse,
                shipping,
                billing,
                itemResponses
        );
    }

    private ContactMechResponse mapContact(ContactMech cm) {
        return new ContactMechResponse(
                cm.getContactMechId(),
                cm.getStreetAddress(),
                cm.getCity(),
                cm.getState(),
                cm.getPostalCode(),
                cm.getPhoneNumber(),
                cm.getEmail()
        );
    }
}
