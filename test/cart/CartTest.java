package cart;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CartTest {

    private Cart cart;

    @Before
    public void setUp() throws Exception {
        cart = new Cart();
    }

    @Test
    public void testGetTotal() throws Exception {

        assertEquals("1 apple",             "£0.60", cart.getTotal("Apple"));
        assertEquals("1 orange",            "£0.25", cart.getTotal("Orange"));
        assertEquals("Not comma separated", "£0.00", cart.getTotal("Apple Apple Orange Apple"));

        assertEquals("prefix, exact word only ", "£0.00", cart.getTotal("toffeeapple"));
        assertEquals("suffix, exact word only",  "£0.00", cart.getTotal("applecrumble"));

        assertEquals("plurals not allowed (only having one item at a time)", "£0.00", cart.getTotal("apples"));

    }


    @Test
    public void testGetTotalWithOffers() throws Exception {

        assertEquals("3 apples & 1 orange", "£1.45", cart.getTotal("Apple, Apple, Orange, Apple"));
        assertEquals("case insensitive",    "£1.45", cart.getTotal("APPLE, apple,   oraNGE   , Apple"));
        assertEquals("3 apples & 3 orange", "£1.70", cart.getTotal("Apple, Apple, Orange, Apple, Orange, Orange"));

    }

    @Test
    public void testGetPriceForInventoryItems_Oranges() throws Exception {

        InventoryItem orange = new InventoryItem("orange", 25);

        assertEquals("1 orange",  25, cart.getPriceForInventoryItems(orange, 1));
        assertEquals("2 orange",  50, cart.getPriceForInventoryItems(orange, 2));
        assertEquals("3 orange",  50, cart.getPriceForInventoryItems(orange, 3));
        assertEquals("4 orange",  75, cart.getPriceForInventoryItems(orange, 4));
        assertEquals("5 orange", 100, cart.getPriceForInventoryItems(orange, 5));
        assertEquals("6 orange", 100, cart.getPriceForInventoryItems(orange, 6));

    }

    @Test
    public void testGetPriceForInventoryItems_Apples() throws Exception {

        InventoryItem apple = new InventoryItem("apple", 60);

        assertEquals("1 apple",  60, cart.getPriceForInventoryItems(apple, 1));
        assertEquals("2 apple",  60, cart.getPriceForInventoryItems(apple, 2));
        assertEquals("3 apple", 120, cart.getPriceForInventoryItems(apple, 3));
        assertEquals("4 apple", 120, cart.getPriceForInventoryItems(apple, 4));
        assertEquals("5 apple", 180, cart.getPriceForInventoryItems(apple, 5));
        assertEquals("6 apple", 180, cart.getPriceForInventoryItems(apple, 6));

    }

}