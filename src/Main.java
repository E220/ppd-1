import inventory.Inventory;
import inventory.InventoryFactory;

public class Main {
    public static void main(String[] args) {
        final Inventory i = InventoryFactory.getNewInventory();
    }
}