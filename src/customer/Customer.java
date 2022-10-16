package customer;

import utils.Random;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Customer {
    private static final int maxItems = 10;

    public Collection<Integer> selectRandomItems(List<Integer> items) {
        final int itemCount = Random.nextInt(maxItems);
        final Collection<Integer> basket = new ArrayList<>(itemCount);
        for (int i = 0; i < itemCount; i++) {
            final int index = Random.nextInt(items.size());
            basket.add(items.get(index));
        }
        return basket;
    }
}
