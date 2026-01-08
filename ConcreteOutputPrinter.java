package Assignment3;

import java.util.ArrayList;

public class ConcreteOutputPrinter implements outputPrinter {

    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void printShoppingList(ArrayList<AbstractShopItem> shoppingList) {
        for (AbstractShopItem item : shoppingList) {
            System.out.printf(
                    "Item: %s, Priority: %d, Price: $%.2f, Quantity: %d%n",
                    item.getName(),
                    item.getPriority(),
                    item.getPrice(),
                    item.getQuantity()
            );
        }
    }

    @Override
    public void printTotalCost(double totalCost) {
        System.out.printf("%nTotal Cost: $%.2f%n", totalCost);
    }

    @Override
    public void printPurchasedItems(ArrayList<AbstractShopItem> shoppingList, double bankAccount) {
        System.out.println("\nNow we go shopping...");
        System.out.println("\nItems Purchased:");

        double remaining = bankAccount;

        for (AbstractShopItem item : shoppingList) {
            double cost = item.getPrice() * item.getQuantity();

            if (cost <= remaining) {
                System.out.printf("Purchased: %s (x%d) for $%.2f%n",
                        item.getName(), item.getQuantity(), cost);
                remaining -= cost;
            }
        }

        System.out.printf("%nYour remaining account balance is: $%.2f%n", remaining);
    }
}
