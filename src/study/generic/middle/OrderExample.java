package study.generic.middle;

import java.util.ArrayList;
import java.util.List;

class Order<T extends Product> {
    private List<T> items = new ArrayList<>();

    public void addItem(T item){
        items.add(item);
    }

    public void printDetailsOrder(){
        System.out.println("Order Details : ");
        for(T item : items){
            System.out.println("- " +item.getDesc()+" : ₩"+item.getPrice());
        }
    }

    public double calculateTotal(){
        double total = 0.0;
        for(T item : items){
            total += item.getPrice();
        }
        return total;
    }
}

class Product{
    private String desc;
    private double price;

    public Product(String desc, double price) {
        this.desc = desc;
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }
    public double getPrice() {
        return price;
    }
}

class Book extends Product {
    private String author;

    public Book(String desc, double price, String author) {
        super(desc, price);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }
}

class Device extends Product {
    private String brand;

    public Device(String desc, double price, String brand) {
        super(desc, price);
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }
}
public class OrderExample {
    public static void main(String[] args) {
        Order<Product> order = new Order<>();

        Book book = new Book("축지법 도술서",20000,"홍길동");
        Device device = new Device("최신형 핸드폰",50000,"삼성");

        order.addItem(book);
        order.addItem(device);

        order.printDetailsOrder();

        System.out.println("Total Order Amount : " + order.calculateTotal());
    }

}
