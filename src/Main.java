import customer.Customer;
import inventory.Inventory;
import inventory.InventoryFactory;
import market.Market;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final Inventory inventory = InventoryFactory.getNewInventory();
        final Market market = new Market(inventory);

        final Customer customer = new Customer(market);
        final Thread thread = new Thread(customer);
        thread.start();
        thread.join();

        System.out.println(market.checkIntegrity());
    }
}