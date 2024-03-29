import customer.Customer;
import inventory.Inventory;
import inventory.InventoryFactory;
import market.Market;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final Inventory inventory = InventoryFactory.getNewInventory();
        final Market market = new Market(inventory);

        final List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final Customer customer = new Customer(market);
            final Thread thread = new Thread(customer);
            threadList.add(thread);
        }

        threadList.forEach(Thread::start);
        for (int i = 0; i < 10; i++) {
            Thread.sleep(100);
            System.out.println(market.checkIntegrity());
        }
        for (Thread thread : threadList) {
            thread.join();
        }

        System.out.println(market.checkIntegrity());
    }
}