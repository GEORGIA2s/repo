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
        System.out.println("Drink Menu:");

        for (Map.Entry<DrinkType, Drink<?>> entry : menu.entrySet()) {
            DrinkType type = entry.getKey();
            Drink<?> drink = entry.getValue();

            System.out.println("  - Type: " + type + " | Drink: " + drink.getName() + " | Price: $" + drink.getPrice());
        }
    }
}

public class DrinkOrderExample {
    public static void main(String[] args) {
        Drink<DrinkType> americano = new Drink<>("Americano", 2.5, DrinkType.COFFEE);
        Drink<DrinkType> greenTea = new Drink<>("Green Tea", 3.0, DrinkType.TEA);
        Drink<DrinkType> cola = new Drink<>("Cola", 1.8, DrinkType.SODA);

        DrinkMenu<DrinkType> cafeMenu = new DrinkMenu<>();
        cafeMenu.addDrink(americano);
        cafeMenu.addDrink(greenTea);
        cafeMenu.addDrink(cola);

        cafeMenu.printMenu();

        DrinkType selectedDrinkType = DrinkType.COFFEE;
        double totalPrice = cafeMenu.getPrice(selectedDrinkType);

        System.out.println("Selected Drink: " + selectedDrinkType);
        System.out.println("Total Price: $" + totalPrice);
    }
}
