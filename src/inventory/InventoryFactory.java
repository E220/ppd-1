package inventory;

import models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InventoryFactory {

    private static final Random random = new Random();

    private static final int productCount = 100;
    private static final int maxDuplicates = 10000;
    private static final int maxPrice = 100;

    public static Inventory getNewInventory() {
        final List<InventoryItem> items = new ArrayList<>();
        for (int p = 0; p < productCount; p++) {
            final int price = random.nextInt(maxPrice);
            final int duplicates = random.nextInt(maxDuplicates);
            final Product product = new Product(price);
            final InventoryItem item = new InventoryItem(product, duplicates);
            items.add(item);
        }
        return new Inventory(items);
    }
}
