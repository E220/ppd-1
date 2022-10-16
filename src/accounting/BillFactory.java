package accounting;

import models.Product;
import utils.QuantityMap;

import java.util.Collection;

public class BillFactory {
    public static Bill billForProducts(Collection<Product> products) {
        final QuantityMap quantities = new QuantityMap(products.size());
        int total = 0;
        for (Product product : products) {
            final int quantity = quantities.getOrDefault(product.getId(), 0);
            quantities.put(product.getId(), quantity + 1);
            total += product.getPrice();
        }
        return new Bill(quantities, total);
    }
}
