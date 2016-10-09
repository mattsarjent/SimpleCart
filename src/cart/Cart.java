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
    protected void loadInventoryCache() {
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
            total += getPriceForInventoryItems(invItem, entry.getValue());
        }

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.UK);
        return currencyFormatter.format(total/100.0);
    }

    /**
     * Get the total price for the number of items including any offers
     * @param invItem  type of item
     * @param numItems number of items of this type
     * @return price in pence
     */
    protected int getPriceForInventoryItems(InventoryItem invItem, Integer numItems) {
        if(invItem.checkName("apple")) {
            // buy one, get one free on Apples
            return invItem.getPriceInPence() * (numItems - (int)((numItems+0.5)/2));
        }
        if (invItem.checkName("orange")) {
            // 3 for the price of 2 on Oranges
            return invItem.getPriceInPence() * (numItems - (numItems/3));
        }
        return invItem.getPriceInPence() * numItems;
    }
}

