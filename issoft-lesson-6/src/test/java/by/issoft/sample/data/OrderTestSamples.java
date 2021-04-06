package by.issoft.sample.data;

import java.util.Date;

public class OrderTestSamples {

    public static Order anyValidOrder() {
        return validOrder("TestUser");
    }

    public static Order validOrder(String userId) {
        Address address = new Address("Belarus", "Hrodno", "Ivye", "Pionerskaya",
                "25A", "17B", 231337);

        Date date = new Date();

        OrderItem orderItem = new OrderItem("Sofa", 1, 599.99);

        return new Order(userId, address, date, orderItem);
    }

    public static Order anyOrder() {
        return anyValidOrder();
    }

}
