package customer;

import market.Market;
import models.Product;
import utils.Random;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Customer implements Runnable {
    private static final int maxItems = 10;

    private final Market market;

    public Customer(Market market) {
        this.market = market;
    }

    @Override
    public void run() {
        final List<Product> products = this.market.getInventory().getProducts();
        final Collection<Product> basket = selectRandomItems(products);
        this.market.buyProducts(basket);
    }

    public <T> Collection<T> selectRandomItems(List<T> items) {
        final int itemCount = Random.nextInt(maxItems);
        final Collection<T> basket = new ArrayList<>(itemCount);
        for (int i = 0; i < itemCount; i++) {
            final int index = Random.nextInt(items.size());
            basket.add(items.get(index));
        }
        return basket;
    }
}
