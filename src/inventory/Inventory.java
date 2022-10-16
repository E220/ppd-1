package inventory;

import utils.QuantityMap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final Map<Integer, InventoryItem> items;

    public Inventory(Collection<InventoryItem> items) {
        this.items = new HashMap<>(items.size());
        items.forEach(item -> this.items.put(item.getId(), item));
    }

    public void remove(QuantityMap quantities) {
        quantities.forEach((id, quantity) -> {
            final InventoryItem item = this.items.get(id);
            item.setQuantity(item.getQuantity() - quantity);
        });
    }

    public boolean hasEnough(QuantityMap quantities) {
        return this.items.entrySet().stream()
                .noneMatch(entry -> quantities.get(entry.getKey()) > entry.getValue().getQuantity());
    }
}
