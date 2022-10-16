package inventory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final Map<Integer, InventoryItem> items;

    public Inventory(Collection<InventoryItem> items) {
        this.items = new HashMap<>(items.size());
        items.forEach(item -> this.items.put(item.getId(), item));
    }
}
