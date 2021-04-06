package by.issoft.sample.data;

public class OrderItem {

    private String id;

    private final String name;

    private final int count;

    private final double price;

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

    public int getCount() {
        return count;
    }

    public double getPrice() {
        return price;
    }

}
