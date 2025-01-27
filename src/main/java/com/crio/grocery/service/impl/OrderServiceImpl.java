package com.crio.grocery.service.impl;

import com.crio.grocery.entity.Customer;
import com.crio.grocery.entity.GroceryItem;
import com.crio.grocery.entity.Order;
import com.crio.grocery.repository.OrderRepository;
import com.crio.grocery.service.CustomerService;
import com.crio.grocery.service.GroceryItemService;
import com.crio.grocery.service.OrderService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private GroceryItemService groceryItemService;

    @Override
    public Order createOrder(Long customerId, List<Long> groceryItemIds) {
        Customer customer = customerService.getCustomerById(customerId);
        List<GroceryItem> groceryItems = groceryItemIds.stream()
            .map(groceryItemService::getGroceryItemById)
            .collect(Collectors.toList());

        Order order = new Order();
        order.setCustomer(customer);
        order.setGroceryItems(groceryItems);
        order.setOrderDate(LocalDate.now());
        order.setTotalPrice(groceryItems.stream().mapToDouble(item -> item.getPrice()*item.getQuantity()).sum());
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Order not found"));
    }

    @Override
    public Order updateOrder(Long id, Order orderDetails) {
        Order order = getOrderById(id);
        order.setCustomer(orderDetails.getCustomer());
        order.setGroceryItems(orderDetails.getGroceryItems());
        order.setOrderDate(orderDetails.getOrderDate());
        order.setTotalPrice(orderDetails.getTotalPrice());
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = getOrderById(id);
        orderRepository.delete(order);
    }
}
