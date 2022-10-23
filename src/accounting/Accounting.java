package accounting;

import utils.QuantityMap;

import java.util.Collection;
import java.util.LinkedList;

public class Accounting {
    private int income = 0;
    private final Collection<Bill> bills = new LinkedList<>();

    public void addBill(Bill bill) {
        this.bills.add(bill);
        income += bill.getTotal();
    }

    public boolean incomeEqualsBillsTotal() {
        int total = this.bills.stream().mapToInt(Bill::getTotal).sum();
        System.out.println(total + "==" + this.income);
        return total == this.income;
    }

    public QuantityMap getQuantities() {
        final QuantityMap quantityMap = new QuantityMap();
        bills.forEach(bill -> bill.getQuantities().forEach((id, quantity) -> {
            final int q = quantityMap.getOrDefault(id, 0);
            quantityMap.put(id, q + quantity);
        }));
        return quantityMap;
    }
}
