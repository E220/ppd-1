package market;

import accounting.Accounting;
import accounting.Bill;
import accounting.BillFactory;
import inventory.Inventory;
import locks.LockManager;
import models.Product;
import utils.QuantityMap;

import java.util.Collection;
import java.util.Set;

public class Market {
    private final Inventory inventory;
    private final Accounting accounting = new Accounting();

    private final LockManager lockManager;

    public Market(Inventory inventory) {
        this.inventory = inventory;
        this.lockManager = new LockManager(this.inventory.getOriginalQuantities().keySet());
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void buyProducts(Collection<Product> products) {
        final Bill bill = BillFactory.billForProducts(products);
        final QuantityMap quantities = bill.getQuantities();
        final Set<Integer> ids = quantities.keySet();

        lockManager.lockForWrite(ids);
        if (inventory.hasEnough(quantities)) {
            inventory.remove(quantities);
            lockManager.unlockAfterWrite(ids);

            lockManager.lockBillsForWrite();
            accounting.addBill(bill);
            lockManager.unlockBillsAfterWrite();
        } else {
            lockManager.unlockAfterWrite(ids);
        }
    }

    public boolean checkIntegrity() {
        final Set<Integer> ids = inventory.getOriginalQuantities().keySet();
        lockManager.lockForRead(ids);
        lockManager.lockBillsForRead();
        final boolean result = this.accounting.incomeEqualsBillsTotal() &&
                this.billsMatchInventory();
        lockManager.unlockBillsAfterRead();
        lockManager.unlockAfterRead(ids);
        return result;
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
