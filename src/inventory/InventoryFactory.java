package inventory;

import models.Product;
import utils.Random;

import java.util.ArrayList;
import java.util.List;

public class InventoryFactory {

    private static final int productCount = 100;
    private static final int maxDuplicates = 10000;
    private static final int maxPrice = 100;

    public static Inventory getNewInventory() {
        final List<InventoryItem> items = new ArrayList<>();
        for (int p = 0; p < productCount; p++) {
            final int price = Random.nextInt(maxPrice);
            final int duplicates = Random.nextInt(maxDuplicates);
            final Product product = new Product(price);
            final InventoryItem item = new InventoryItem(product, duplicates);
            items.add(item);
        }
        return new Inventory(items);
    }
}
