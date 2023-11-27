package study.level.middle;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

// 이넘(Enum) 정의
enum OrderStatus {
    PENDING, PROCESSING, COMPLETED, CANCELED
}

// 제네릭 클래스 정의
class Order<T extends Number> {
    private String orderId;
    private OrderStatus status;
    private List<T> items;

    public Order(String orderId) {
        this.orderId = orderId;
        this.status = OrderStatus.PENDING;
        this.items = new ArrayList<>();
    }

    public void addItem(T item) {
        items.add(item);
    }

    public String getOrderId() {
        return orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<T> getItems() {
        return items;
    }

    // 제네릭 메서드와 어노테이션을 혼합한 메서드
    @UpdateStatus(OrderStatus.PROCESSING)
    public void processOrder() {
        if (status == OrderStatus.PENDING) {
            status = OrderStatus.PROCESSING;
            System.out.println("Order " + orderId + " is now being processed.");
        } else {
            System.out.println("Cannot process order with status: " + status);
        }
    }
}

// 어노테이션 정의
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface UpdateStatus {
    OrderStatus value();
}

public class OrderManageExample {
    public static void main(String[] args) {
        // 주문 생성
        Order<Integer> order1 = new Order<>("A123");
        order1.addItem(1001);
        order1.addItem(1002);

        // 주문 처리
        order1.processOrder();

        // 주문 상태 출력
        System.out.println("Order ID: " + order1.getOrderId());
        System.out.println("Order Status: " + order1.getStatus());
        System.out.println("Order Items: " + order1.getItems());
    }
}
