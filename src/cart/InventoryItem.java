package cart;


public class InventoryItem {
    private final String name;
    private final int priceInPence;

    public InventoryItem(String name, int priceInPence) {
        this.name = name;
        this.priceInPence = priceInPence;
    }

    public boolean checkName(String otherName) {
        otherName = otherName.trim();
        return name.equalsIgnoreCase(otherName);
    }

    public int getPriceInPence() {
        return priceInPence;
    }
}
