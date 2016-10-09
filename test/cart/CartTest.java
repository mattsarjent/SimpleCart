package cart;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
        assertEquals("3 apples & 1 orange", "£2.05", cart.getTotal("Apple, Apple, Orange, Apple"));
        assertEquals("case insensitive",    "£2.05", cart.getTotal("APPLE, apple,   oraNGE   , Apple"));
        assertEquals("Not comma separated", "£0.00", cart.getTotal("Apple Apple Orange Apple"));

        assertEquals("prefix, exact word only ", "£0.00", cart.getTotal("toffeeapple"));
        assertEquals("suffix, exact word only",  "£0.00", cart.getTotal("applecrumble"));

        assertEquals("plurals not allowed (only having one item at a time)", "£0.00", cart.getTotal("apples"));

    }
}