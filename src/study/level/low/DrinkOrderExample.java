package study.level.low;

import java.util.HashMap;
import java.util.Map;

enum DrinkType {
    COFFEE,
    TEA,
    SODA
}

class Drink<T extends Enum<T>> {
    private String name;
    private double price;
    private T type;

    public Drink(String name, double price, T type) {
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

class DrinkMenu<T extends Enum<T>> {
    private Map<DrinkType, Drink<?>> menu;

    public DrinkMenu() {
        this.menu = new HashMap<>();
    }

    public void addDrink(Drink<?> drink) {
        menu.put((DrinkType) drink.getType(), drink);
    }

    public double getPrice(DrinkType type) {
        return menu.get(type).getPrice();
    }

    public void printMenu() {
        System.out.println("음료 메뉴:");

        for (Map.Entry<DrinkType, Drink<?>> entry : menu.entrySet()) {
            DrinkType type = entry.getKey();
            Drink<?> drink = entry.getValue();

            System.out.println("  - 타입: " + type + " | 음료: " + drink.getName() + " | 가격: ₩" + (int)drink.getPrice());
        }
    }
}

public class DrinkOrderExample {
    public static void main(String[] args) {
        Drink<DrinkType> americano = new Drink<>("아메리카노", 2500, DrinkType.COFFEE);
        Drink<DrinkType> greenTea = new Drink<>("녹차", 3000, DrinkType.TEA);
        Drink<DrinkType> cola = new Drink<>("콜라", 1800, DrinkType.SODA);

        DrinkMenu<DrinkType> cafeMenu = new DrinkMenu<>();
        cafeMenu.addDrink(americano);
        cafeMenu.addDrink(greenTea);
        cafeMenu.addDrink(cola);

        cafeMenu.printMenu();

        DrinkType selectedDrinkType = DrinkType.COFFEE;
        double totalPrice = cafeMenu.getPrice(selectedDrinkType);

        System.out.println("선택된 음료: " + selectedDrinkType);
        System.out.println("총 금액: ₩" + (int)totalPrice);
    }
}
