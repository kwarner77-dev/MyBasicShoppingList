package Assignment3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MyShoppingList {

    private static final int Max_Items = 7;
    private static final double initialbankAccount = 59.00;

    private static outputPrinter outputprint = new ConcreteOutputPrinter();

    public static void main(String[] args) {

        String filePath = "shopping_list.txt";

        // Option A: we actually USE the loaded list
        ArrayList<AbstractShopItem> shoppingList;
        try {
            shoppingList = FileUtility.readShoppingListFromFile(filePath);
            outputprint.printMessage("Prior Shopping List has been loaded.");
        } catch (IOException e) {
            shoppingList = new ArrayList<>();
            outputprint.printMessage("No prior list found / could not read file: " + e.getMessage());
        }

        Scanner keyboard = new Scanner(System.in);

        double totalCost = 0;
        double bankAccount = initialbankAccount;

        outputprint.printMessage("Please enter your name:");
        String userName = keyboard.nextLine();

        while (!userName.matches("[a-zA-Z ]+")) {
            outputprint.printMessage("Invalid input. Your name can only contain letters. Please re-enter your name:");
            userName = keyboard.nextLine();
        }

        outputprint.printMessage("\n" + userName + ", your current bank account balance is: $" + String.format("%.2f", bankAccount));
        outputprint.printMessage("\nEnter shopping items, their prices, and priorities! ");

        for (int i = 0; i < Max_Items; i++) {

            try {
                outputprint.printMessage("Enter an item name: ");
                String name = keyboard.nextLine().trim();

                if (!name.matches("[a-zA-Z ]+")) {
                    throw new ShoppingException("That is an invalid input. Please enter an item name.");
                }

                int itemPriority;
                while (true) {
                    outputprint.printMessage("Enter a priority (1 being the greatest): ");
                    try {
                        itemPriority = keyboard.nextInt();
                        keyboard.nextLine();

                        if (itemPriority <= 0) {
                            throw new ShoppingException("Invalid input. Priority must be an integer greater than 0.");
                        }
                        break;
                    } catch (InputMismatchException e) {
                        outputprint.printMessage("Invalid input. Please enter a valid number for priority.");
                        keyboard.nextLine();
                    } catch (ShoppingException e) {
                        outputprint.printMessage(e.getMessage());
                    }
                }

                double itemPrice;
                while (true) {
                    outputprint.printMessage("Enter a price for " + name + " (in dollars): ");
                    try {
                        itemPrice = keyboard.nextDouble();
                        keyboard.nextLine();

                        if (itemPrice <= 0) {
                            throw new ShoppingException("That is an invalid input. Please enter a price greater than 0.");
                        }
                        break;
                    } catch (InputMismatchException e) {
                        outputprint.printMessage("Invalid input. Please enter a valid number.");
                        keyboard.nextLine();
                    } catch (ShoppingException e) {
                        outputprint.printMessage(e.getMessage());
                    }
                }

                int itemQuantity;
                while (true) {
                    outputprint.printMessage("Enter the quantity of " + name + ":");
                    try {
                        itemQuantity = keyboard.nextInt();
                        keyboard.nextLine();

                        if (itemQuantity <= 0) {
                            throw new ShoppingException("Invalid input. Quantity must be greater than 0.");
                        }
                        break;
                    } catch (InputMismatchException e) {
                        outputprint.printMessage("Invalid input. Please enter a valid integer for item quantity.");
                        keyboard.nextLine();
                    } catch (ShoppingException e) {
                        outputprint.printMessage(e.getMessage());
                    }
                }

                // ✅ Option A: one object per item name
                AbstractShopItem existing = itemRepeatCheck.findExisting(shoppingList, name);

                if (existing != null) {
                    existing.setQuantity(existing.getQuantity() + itemQuantity);
                    // If you want, you could also update price/priority here (optional).
                    // existing.setPrice(itemPrice);
                } else {
                    shoppingList.add(new ConcreteShopItem(name, itemPriority, itemPrice, itemQuantity));
                }

                totalCost += itemPrice * itemQuantity;

            } catch (ShoppingException e) {
                outputprint.printMessage(e.getMessage());
                i--; // retry current item
            }
        }

        // Bubble sort by priority (lower number = higher priority)
        for (int i = 0; i < shoppingList.size() - 1; i++) {
            for (int j = 0; j < shoppingList.size() - i - 1; j++) {
                if (shoppingList.get(j).getPriority() > shoppingList.get(j + 1).getPriority()) {
                    AbstractShopItem temp = shoppingList.get(j);
                    shoppingList.set(j, shoppingList.get(j + 1));
                    shoppingList.set(j + 1, temp);
                }
            }
        }

        try {
            FileUtility.writeShoppingListToFile(filePath, shoppingList);
            outputprint.printMessage("Shopping list saved to " + filePath);
        } catch (IOException e) {
            outputprint.printMessage("Error writing file: " + e.getMessage());
        }

        outputprint.printMessage("\n" + userName + "'s Shopping List: ");
        outputprint.printShoppingList(shoppingList);
        outputprint.printTotalCost(totalCost);
        outputprint.printPurchasedItems(shoppingList, bankAccount);

        keyboard.close();
    }
}
		