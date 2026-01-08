package Assignment3;

import java.io.*;
import java.util.ArrayList;

public class FileUtility {

    // File format: name,priority,price,quantity
    public static ArrayList<AbstractShopItem> readShoppingListFromFile(String filePath) throws IOException {
        ArrayList<AbstractShopItem> shoppingList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String name = parts[0].trim();
                    int priority = Integer.parseInt(parts[1].trim());
                    double price = Double.parseDouble(parts[2].trim());
                    int quantity = Integer.parseInt(parts[3].trim());

                    shoppingList.add(new ConcreteShopItem(name, priority, price, quantity));
                }
            }
        }

        return shoppingList;
    }

    public static void writeShoppingListToFile(String filePath, ArrayList<AbstractShopItem> shoppingList) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (AbstractShopItem item : shoppingList) {
                String line = String.format("%s,%d,%.2f,%d%n",
                        item.getName(), item.getPriority(), item.getPrice(), item.getQuantity());
                writer.write(line);
            }
        }
    }
}

