package by.issoft.sample.data;

public class OrderTestSamples {

    public static Order anyValidOrder() {
        return validOrder("TestUser");
    }

    public static Order validOrder(String userId) {
        Order order = new Order(userId);

        order.setAddress(new Address("Belarus", "Hrodno", "Ivye", "Pionerskaya",
                "25A", "17B", 231337));

        order.setItems(new OrderItem("Sofa", 1, 599.99));

        return order;
    }

    public static Order anyOrder() {
        return anyValidOrder();
    }

}
