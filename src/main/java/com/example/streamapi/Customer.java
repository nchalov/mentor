package com.example.streamapi;

import java.util.Set;

public final class Customer {

    private final Long id;
    private final String name;
    private final Long level;
    private final Set<Order> orders;

    public Customer(Long id, String name, Long level, Set<Order> orders) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getLevel() {
        return level;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return name;
    }
}
