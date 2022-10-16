package inventory;

import models.Product;

public class InventoryItem {
    private final Product product;
    private final int quantity;

    public InventoryItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public int getId() {
        return this.product.getId();
    }

    public int getPrice() {
        return this.product.getPrice();
    }

    public int getQuantity() {
        return this.quantity;
    }
}
