package com.crio.grocery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crio.grocery.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
