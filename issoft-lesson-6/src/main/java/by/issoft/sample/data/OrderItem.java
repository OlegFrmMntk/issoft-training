package by.issoft.sample.data;

public class OrderItem {

    private String id;

    private String name;

    private int count;

    private double price;

    public OrderItem(String name, int count, double price) {
        this.name = name;
        this.count = count;
        this.price = price;
    }

    public static OrderItem of(String name, int count, double price) {
        return new OrderItem(name, count, price);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
