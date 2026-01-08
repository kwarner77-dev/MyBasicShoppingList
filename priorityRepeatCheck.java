package Assignment3;

import java.util.ArrayList;

public class priorityRepeatCheck {

    public static boolean isPriorityRepeated(ArrayList<AbstractShopItem> shoppingList, int itemPriority) {
        for (AbstractShopItem item : shoppingList) {
            if (item != null && item.getPriority() == itemPriority) {
                return true;
            }
        }
        return false;
    }
}
