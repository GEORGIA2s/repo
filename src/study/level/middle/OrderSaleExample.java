package study.level.middle;

import java.util.ArrayList;
import java.util.List;

enum ProductType {
    전자제품,
    의류,
    도서
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
            case 전자제품:
                return 0.1; // 전자제품은 10% 할인
            case 의류:
                return 0.05; // 의류는 5% 할인
            case 도서:
                return 0.2; // 도서는 20% 할인
            default:
                return 0;
        }
    }
}

public class OrderSaleExample {
    public static void main(String[] args) {
        Product<ProductType> laptop = new Product<>("노트북", 1000000, ProductType.전자제품);
        Product<ProductType> shirt = new Product<>("셔츠", 50000, ProductType.의류);
        Product<ProductType> novel = new Product<>("소설", 20000, ProductType.도서);

        Order_2<Product<ProductType>> order = new Order_2<>();
        order.addItem(laptop);
        order.addItem(shirt);
        order.addItem(novel);

        double totalPrice = order.calculateTotalPrice();
        System.out.println("할인 후 총 가격: ₩" + totalPrice);
    }
