package Assignment3;

import java.util.ArrayList;

public class itemRepeatCheck {

    // Option A: return the existing item object if it exists, otherwise null
    public static AbstractShopItem findExisting(ArrayList<AbstractShopItem> shoppingList, String name) {
        for (AbstractShopItem item : shoppingList) {
            if (item != null && item.getName().equalsIgnoreCase(name.trim())) {
                return item;
            }
        }
        return null;
    }
}