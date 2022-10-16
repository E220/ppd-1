package accounting;

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
        return total == this.income;
    }
}
