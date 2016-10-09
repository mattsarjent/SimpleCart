package cart;

import java.text.NumberFormat;
import java.util.*;

public class Cart {

    private ArrayList<InventoryItem> inventory = new ArrayList<>(2);

    public Cart() {
        loadInventoryCache();
    }

    /**
     * Load the known items sold into the cache
     */
    private void loadInventoryCache() {
        inventory.add(new InventoryItem("apple", 60));
        inventory.add(new InventoryItem("orange", 25));
    }

    /**
     * Get total price of items in a comma separated list
     *
     * The items in the list are parsed ignoring the case. Only one item per scan ie "orange, 2 apples" the 2 apples
     * will not be use. There will be no error messages, the cart system will just do the best lookup it can.
     * The output will be a string so the correct rounded price will be displayed (String input so string output)
     *
     * @param conveyorItems is a string containing a comma separated list of groceries
     * @return a string of the price in Pounds
     */
    public String getTotal(String conveyorItems) {
        Map<InventoryItem, Integer> basket = new HashMap<>();
        String[] items = conveyorItems.split(",");
        for (String item : items) {
            for (InventoryItem invItem: inventory) {
                if (invItem.checkName(item)){
                    basket.put(invItem, basket.getOrDefault(invItem, 0) + 1);
                }
            }
        }

        int total = 0;
        for (Map.Entry<InventoryItem, Integer> entry : basket.entrySet()) {
            InventoryItem invItem = entry.getKey();
            total += invItem.getPriceInPence() * entry.getValue();
        }

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.UK);
        return currencyFormatter.format(total/100.0);
    }
}
