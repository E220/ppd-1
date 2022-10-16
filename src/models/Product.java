package models;

public class Product extends Entity {
    private final int price;

    public Product(int price) {
        super();
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
