package by.issoft.sample.data;

import java.util.Date;

public class Order {

    private String id;

    private OrderItem[] items;

    private OrderStatus status;

    private final String userId;

    private final Address address;

    private final Date date;

    public Order(String userId, Address address, Date date, OrderItem... items) {
        this.userId = userId;
        this.date = date;
        this.items = items;
        this.address = address;
    }


    public void setId(String id) { this.id = id; }

    public void setItems(OrderItem... items) {
        this.items = items;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public OrderItem[] getItems() {
        return items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Date getDate() {
        return date;
    }

    public Address getAddress() {
        return address;
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (OrderItem item : items) {
            totalPrice += item.getPrice() * item.getCount();
        }

        return totalPrice;
    }

}
