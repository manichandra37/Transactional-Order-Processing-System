package com.mani.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mani.orders.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

    
}
