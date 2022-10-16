package accounting;

import models.Entity;
import utils.QuantityMap;

import java.util.Map;

public class Bill extends Entity {
    private final QuantityMap quantities;
    private final int total;

    public Bill(QuantityMap quantities, int total) {
        this.quantities = quantities;
        this.total = total;
    }

    public int getTotal() {
        return this.total;
    }

    public QuantityMap getQuantities() {
        return this.quantities;
    }
}
