package market;

import accounting.Accounting;
import accounting.Bill;
import accounting.BillFactory;
import inventory.Inventory;
import models.Product;
import utils.QuantityMap;

import java.util.Collection;

public class Market {
    private final Inventory inventory;
    private final Accounting accounting = new Accounting();

    public Market(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void buyProducts(Collection<Product> products) {
        final Bill bill = BillFactory.billForProducts(products);
        if (inventory.hasEnough(bill.getQuantities())) {
            inventory.remove(bill.getQuantities());
            accounting.addBill(bill);
        }
    }

    public boolean checkIntegrity() {
        return this.accounting.incomeEqualsBillsTotal() &&
                this.billsMatchInventory();
    }

    private boolean billsMatchInventory() {
        final QuantityMap current = inventory.getQuantities();
        final QuantityMap original = inventory.getOriginalQuantities();
        final QuantityMap sold = accounting.getQuantities();
        return original.keySet().stream().allMatch(
                id -> original.get(id) - sold.getOrDefault(id, 0) == current.get(id)
        );
    }
}
