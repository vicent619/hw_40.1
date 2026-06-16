package app.repository;

import app.model.Order;
import app.model.Product;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class OrderRepository {
    private final Map<Long, Order> orders = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(0);

    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(orders.get(id));
    }

    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }

    public Order save(Order order) {
        completeOrder(order);
        orders.put(order.getId(), order);
        return order;
    }

    private void completeOrder(Order order) {
        if (order.getId() == null) order.setId(idCounter.incrementAndGet());
        if (order.getCreationDate() == null) order.setCreationDate(Instant.now());
        List<Product> products = order.getProducts();
        BigDecimal totalCost = products == null ? BigDecimal.ZERO : products.stream()
                .map(Product::getCost)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalCost(totalCost);
    }
}
