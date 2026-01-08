package Assignment3;

import java.util.ArrayList;

public interface outputPrinter {

    void printMessage(String message);

    void printShoppingList(ArrayList<AbstractShopItem> shoppingList);

    void printTotalCost(double totalCost);

    void printPurchasedItems(ArrayList<AbstractShopItem> shoppingList, double remainingBudget);
}