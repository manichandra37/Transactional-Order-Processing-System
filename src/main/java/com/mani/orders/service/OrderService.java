package com.mani.orders.service;

import org.springframework.stereotype.Service;

import com.mani.orders.entity.Order;
import com.mani.orders.entity.OrderStatus;
import com.mani.orders.exception.InvalidOrderStateException;
import com.mani.orders.exception.OrderNotFoundException;
import com.mani.orders.repository.OrderRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    @Transactional
    public Order createOrder(Order order) {

        order.setStatus(OrderStatus.CREATED);
        return orderRepository.save(order);
    }

    @Transactional
    public Order confirmOrder(Long orderId){

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));

        if(order.getStatus() != OrderStatus.CREATED){
            throw new InvalidOrderStateException("Order is not in a valid state for confirmation with current state: " + order.getStatus());
        }
        order.setStatus(OrderStatus.CONFIRMED);
        return orderRepository.save(order);

    }

    @Transactional
    public Order cancelOrder(Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));

        if(order.getStatus() != OrderStatus.CREATED){
            throw new InvalidOrderStateException("Order is not in a valid state for cancellation with current state: " + order.getStatus());
        }
        order.setStatus(OrderStatus.CANCELLED);
        return orderRepository.save(order);
    }
}
