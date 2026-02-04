package com.mani.orders.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    public Order order;

    @Column(nullable = false)
    public String productCode;

    @Column(nullable = false)
    public int quantity;

    @Column(nullable = false)
    public BigDecimal unitPrice;
}
