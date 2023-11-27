package study.level.middle;

import java.util.ArrayList;
import java.util.List;

enum ProductType {
    ELECTRONICS,
    CLOTHING,
    BOOKS
}

class Product<T> {
    private String name;
    private double price;
    private T type;

    public Product(String name, double price, T type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public T getType() {
        return type;
    }
}

class Order_2<T extends Product<?>> {
    private List<T> items;

    public Order_2() {
        this.items = new ArrayList<>();
    }

    public void addItem(T item) {
        items.add(item);
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;

        for (T item : items) {
            double discount = getDiscount((ProductType) item.getType());
            totalPrice += item.getPrice() * (1 - discount);
        }

        return totalPrice;
    }

    public double getDiscount(ProductType type) {
        switch (type) {
            case ELECTRONICS:
                return 0.1; // 10% discount for electronics
            case CLOTHING:
                return 0.05; // 5% discount for clothing
            case BOOKS:
                return 0.2; // 20% discount for books
            default:
                return 0;
        }
    }
}

public class OrderSaleExample {
    public static void main(String[] args) {
        Product<ProductType> laptop = new Product<>("Laptop", 1000, ProductType.ELECTRONICS);
        Product<ProductType> shirt = new Product<>("Shirt", 50, ProductType.CLOTHING);
        Product<ProductType> novel = new Product<>("Novel", 20, ProductType.BOOKS);

        Order_2<Product<ProductType>> order = new Order_2<>();
        order.addItem(laptop);
        order.addItem(shirt);
        order.addItem(novel);

        double totalPrice = order.calculateTotalPrice();
        System.out.println("Total Price after discount: $" + totalPrice);
    }
}