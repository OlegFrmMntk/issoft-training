package by.issoft.sample.data;

public class OrderItemTestSamples {

    public static OrderItem anyValidOrderItem() {
        return OrderItem.of("Sofa", 1, 599.99);
    }

    public static OrderItem validOrderItem(String name, int count, double price) {
        return new OrderItem(name, count, price);
    }

    public static OrderItem anyOrderItem() {
        return anyValidOrderItem();
    }

}
