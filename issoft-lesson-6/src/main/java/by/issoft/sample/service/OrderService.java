package by.issoft.sample.service;

import by.issoft.sample.data.Order;
import by.issoft.sample.data.OrderStatus;
import by.issoft.sample.persistence.OrderStorage;

public class OrderService {

    private final OrderValidator orderValidator;
    private final OrderStorage orderStorage;

    public OrderService(OrderValidator orderValidator, OrderStorage orderStorage) {
        this.orderValidator = orderValidator;
        this.orderStorage = orderStorage;
    }

    public String placeOrder(Order order) {

        boolean isValidOrder = orderValidator.validateForCreation(order);
        if (!isValidOrder) {
            throw new IllegalArgumentException("Order is not valid: " + order);
        }

        order.setStatus(OrderStatus.INITIAL);

        final String id = orderStorage.persist(order);
        order.setId(id);

        return id;
    }

    public String[] loadAllByUserId(String userId, Order... orders) {

        String[] ordersId = new String[orders.length];

        for (int i = 0; i < orders.length; i++) {
            orders[i].setUserId(userId);

            boolean isValidOrder = orderValidator.validateForCreation(orders[i]);
            if (!isValidOrder) {
                throw new IllegalArgumentException("Order is not valid: " + orders[i]);
            }

            final String id = orderStorage.persist(orders[i]);
            orders[i].setId(id);

            ordersId[i] = id;
        }

        return ordersId;
    }
}
