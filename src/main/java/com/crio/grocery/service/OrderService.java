package com.crio.grocery.service;

import java.util.List;

import com.crio.grocery.entity.Order;

public interface OrderService {

    Order createOrder(Long customerId, List<Long> groceryItemIds);

    List<Order> getAllOrders();

    Order getOrderById(Long id);

    Order updateOrder(Long id, Order orderDetails);

    void deleteOrder(Long id);
}