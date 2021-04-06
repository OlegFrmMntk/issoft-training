package by.issoft.sample.service;

import by.issoft.sample.data.Order;
import by.issoft.sample.data.OrderStatus;
import by.issoft.sample.persistence.OrderItemStorage;
import by.issoft.sample.persistence.OrderStorage;

import static by.issoft.sample.data.OrderTestSamples.anyOrder;
import static by.issoft.sample.data.OrderTestSamples.validOrder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.*;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

class OrderServiceTest {

    private OrderValidator orderValidator;

    private OrderStorage orderStorage;

    private OrderItemStorage orderItemStorage;

    private OrderService orderService;

    @BeforeEach
    public void before() {
        orderValidator = mock(OrderValidator.class);
        orderStorage = mock(OrderStorage.class);
        orderItemStorage = mock(OrderItemStorage.class);

        when(orderStorage.persist(any())).thenReturn(UUID.randomUUID().toString());

        orderService = new OrderService(orderValidator, orderStorage, orderItemStorage);
    }

    @Test
    void createOrder_invalid() {
        Order order = anyOrder();
        orderIsValid(order);

        assertThrows(IllegalArgumentException.class, () -> orderService.placeOrder(order));

        verify(orderStorage, never()).persist(any());
    }

    @Test
    void createOrder() {
        String userId = "TestUserId";

        Order order = validOrder(userId);

        when(orderValidator.validateForCreation(order)).thenReturn(true);

        final String id = orderService.placeOrder(order);

        assertNotNull(id);
        verify(orderStorage).persist(order);
        assertThat(order.getStatus(), is(OrderStatus.INITIAL));
    }

    void orderIsValid(Order order) {
        when(orderValidator.validateForCreation(order)).thenReturn(false);
    }
}