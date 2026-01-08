package Assignment3;

public class ConcreteShopItem extends AbstractShopItem {

    // Option A: quantity is stored in AbstractShopItem
    public ConcreteShopItem(String itemName, int itemPriority, double itemPrice, int quantity) {
        super(itemName, itemPriority, itemPrice);
        setQuantity(quantity);
    }

    // Default quantity = 1
    public ConcreteShopItem(String itemName, int itemPriority, double itemPrice) {
        this(itemName, itemPriority, itemPrice, 1);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ConcreteShopItem other = (ConcreteShopItem) obj;
        return getName().equalsIgnoreCase(other.getName());
    }

    @Override
    public int hashCode() {
        return getName().toLowerCase().hashCode();
    }
}