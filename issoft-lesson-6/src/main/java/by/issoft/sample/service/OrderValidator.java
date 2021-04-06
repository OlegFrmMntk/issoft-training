package by.issoft.sample.service;

import by.issoft.sample.data.Order;


public class OrderValidator {
    public boolean validateForCreation(Order order) {
         return order.getUserId() != null && order.getAddress() != null && order.getItems() != null
                 && order.getItems().length != 0;
    }
}
