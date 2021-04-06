package by.issoft.sample.data;

import java.util.Date;

public class Order {

    private String id;

    private String userId;

    private OrderItem[] items;

    private Address address;

    private OrderStatus status;

    private Date date;

    private double totalPrice;

    public Order(String userId) {
        this.userId = userId;
    }

    public Order(String userId, Address address, OrderItem... items) {
        this.userId = userId;
        this.items = items;
        this.address = address;
    }


    public void setId(String id) { this.id = id; }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setItems(OrderItem... items) {
        this.items = items;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAddress(Address address) {
        this.address = address;
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
        calculateTotalPrice();

        return totalPrice;
    }

    private void calculateTotalPrice() {
        this.totalPrice = 0;
        for (OrderItem item : items) {
            this.totalPrice += item.getPrice() * item.getCount();
        }
    }
}
