import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

// 제품 종류를 정의하는 이넘
enum ProductType {
    ELECTRONICS, CLOTHING, BOOKS
}

// 상품을 나타내는 클래스
class Product<T> {
    private T data;
    private double price;

    public Product(T data, double price) {
        this.data = data;
        this.price = price;
    }

    public T getData() {
        return data;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "data=" + data +
                ", price=" + price +
                '}';
    }
}

// 상점의 재고를 관리하는 클래스
class InventoryManager<T> {
    private EnumMap<ProductType, Map<Product<T>, Integer>> inventory;

    public InventoryManager() {
        this.inventory = new EnumMap<>(ProductType.class);
        initializeInventory();
    }

    private void initializeInventory() {
        for (ProductType type : ProductType.values()) {
            inventory.put(type, new HashMap<>());
        }
    }

    public void addProduct(ProductType type, Product<T> product, int quantity) {
        Map<Product<T>, Integer> productMap = inventory.get(type);
        productMap.put(product, productMap.getOrDefault(product, 0) + quantity);
    }

    public void sellProduct(ProductType type, Product<T> product, int quantity) {
        Map<Product<T>, Integer> productMap = inventory.get(type);
        if (productMap.containsKey(product)) {
            int availableQuantity = productMap.get(product);
            if (availableQuantity >= quantity) {
                productMap.put(product, availableQuantity - quantity);
                System.out.println("판매 완료: " + product.getData() + " - 수량: " + quantity);
            } else {
                System.out.println("재고 부족으로 판매 실패: " + product.getData());
            }
        } else {
            System.out.println("해당 상품이 재고에 없습니다: " + product.getData());
        }
    }

    public void printInventory() {
        System.out.println("----- 상점 재고 현황 -----");
        for (ProductType type : ProductType.values()) {
            Map<Product<T>, Integer> productMap = inventory.get(type);
            System.out.println(type + " : " + productMap);
        }
        System.out.println("------------------------");
    }
}

public class InventoryExample {
    public static void main(String[] args) {
        // 상품 생성
        Product<String> laptop = new Product<>("Laptop", 1200.00);
        Product<String> tShirt = new Product<>("T-Shirt", 25.00);
        Product<String> javaBook = new Product<>("Java Programming", 50.00);

        // 상점 재고 관리자 생성
        InventoryManager<String> inventoryManager = new InventoryManager<>();

        // 상품 추가
        inventoryManager.addProduct(ProductType.ELECTRONICS, laptop, 10);
        inventoryManager.addProduct(ProductType.CLOTHING, tShirt, 50);
        inventoryManager.addProduct(ProductType.BOOKS, javaBook, 20);

        // 상점 재고 현황 출력
        inventoryManager.printInventory();

        // 상품 판매
        inventoryManager.sellProduct(ProductType.ELECTRONICS, laptop, 3);
        inventoryManager.sellProduct(ProductType.CLOTHING, tShirt, 30);
        inventoryManager.sellProduct(ProductType.BOOKS, javaBook, 25);

        // 업데이트된 상점 재고 현황 출력
        inventoryManager.printInventory();
    }
}
