package by.issoft.sample.service;

import by.issoft.sample.data.Order;
import by.issoft.sample.data.OrderItem;
import by.issoft.sample.data.OrderStatus;
import by.issoft.sample.persistence.OrderItemStorage;
import by.issoft.sample.persistence.OrderStorage;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.google.inject.internal.util.Preconditions.checkState;

public class OrderService {

    private static Logger logger = Logger.getLogger(OrderService.class);

    private final OrderValidator orderValidator;
    private final OrderStorage orderStorage;
    private final OrderItemStorage orderItemStorage;

    public OrderService(OrderValidator orderValidator, OrderStorage orderStorage, OrderItemStorage orderItemStorage) {
        this.orderValidator = orderValidator;
        this.orderStorage = orderStorage;
        this.orderItemStorage = orderItemStorage;
    }

    public String placeOrder(Order order) {

        boolean isValidOrder = orderValidator.validateForCreation(order);
        if (!isValidOrder) {
            throw new IllegalArgumentException("Order is not valid: " + order);
        }

        order.setStatus(OrderStatus.INITIAL);

        final String orderId = orderStorage.persist(order);
        order.setId(orderId);

        logger.info(String.format("Order added successfully = %s; id = %s.", order, orderId));

        OrderItem[] items = order.getItems();
        for (OrderItem item : items) {
            final String itemId = orderItemStorage.persist(item);
            item.setId(itemId);

            logger.info(String.format("Item added successfully = %s; id = %s.", item, itemId));
        }

        order.setItems(items);

        return orderId;
    }

    public OrderItem[] loadAllByUserId(String userId) {

        Order[] ordersByUserId = orderStorage.findByUserId(userId);

        checkState(ordersByUserId == null, "User does not exist");

        logger.info(String.format("Orders load successfully = %s.", ordersByUserId.toString()));

        List<OrderItem> items = new ArrayList<OrderItem>();
        for (Order order : ordersByUserId) {
            OrderItem[] orderItems = order.getItems();

            for (OrderItem item : orderItems) {
                OrderItem orderItem = orderItemStorage.load(item.getId());

                logger.info(String.format("Item load successfully = %s.", orderItem.toString()));

                items.add(orderItem);
            }
        }

        return (OrderItem[]) items.toArray();
    }
}
