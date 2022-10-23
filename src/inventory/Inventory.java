package inventory;

import models.Product;
import utils.QuantityMap;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    private final Map<Integer, InventoryItem> items;
    private final QuantityMap originalQuantities;

    public Inventory(Collection<InventoryItem> items) {
        this.items = new HashMap<>(items.size());
        items.forEach(item -> this.items.put(item.getId(), item));
        originalQuantities = this.getQuantities();
    }

    public void remove(QuantityMap quantities) {
        quantities.forEach((id, quantity) -> {
            final InventoryItem item = this.items.get(id);
            item.setQuantity(item.getQuantity() - quantity);
        });
    }

    public boolean hasEnough(QuantityMap quantities) {
        return this.items.entrySet().stream()
                .noneMatch(entry -> quantities.getOrDefault(entry.getKey(), 0) > entry.getValue().getQuantity());
    }

    public List<Product> getProducts() {
        return items.values().stream().map(InventoryItem::getProduct).toList();
    }

    public QuantityMap getQuantities() {
        final QuantityMap quantityMap = new QuantityMap(items.size());
        items.values().forEach(item -> quantityMap.put(item.getId(), item.getQuantity()));
        return quantityMap;
    }

    public QuantityMap getOriginalQuantities() {
        return originalQuantities;
    }
}
