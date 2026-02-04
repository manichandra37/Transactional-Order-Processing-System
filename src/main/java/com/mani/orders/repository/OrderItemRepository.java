package com.mani.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mani.orders.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    
}
