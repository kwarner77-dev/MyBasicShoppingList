package Assignment3;

/**
 * Abstract base item: name, priority, price, quantity.
 * Option A rule: quantity is stored ON the object (not duplicates in a list).
 */
abstract class AbstractShopItem implements itemInterface, ItemOperations {

    private final String itemName;
    private double itemPrice;
    private final int itemPriority;
    private int itemQuantity;

    public AbstractShopItem(String itemName, int itemPriority, double itemPrice) {
        this.itemName = itemName;
        this.itemPriority = itemPriority;
        this.itemPrice = itemPrice;
        this.itemQuantity = 1; // default
    }

    @Override
    public String getName() {
        return itemName;
    }

    @Override
    public double getPrice() {
        return itemPrice;
    }

    @Override
    public int getPriority() {
        return itemPriority;
    }

    public int getQuantity() {
        return itemQuantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be >= 1");
        }
        this.itemQuantity = quantity;
    }

    @Override
    public void setPrice(double newPrice) {
        if (newPrice <= 0) {
            throw new IllegalArgumentException("Price must be > 0");
        }
        this.itemPrice = newPrice;
    }

    @Override
    public void updatePrice(double newPrice) {
        setPrice(newPrice);
    }
}
