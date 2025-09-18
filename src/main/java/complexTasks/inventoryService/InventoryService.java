package complexTasks.inventoryService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InventoryService {
    private static boolean isInventoryOpen = false;

    private Map<String, List<Product>> itemsMap = new HashMap<>();

    public Map<String, List<Product>> getItemsMap() {
        return itemsMap;
    }

    public void addItem(Product itemToAdd) {
        if (!isInventoryOpen) System.out.println("Inventory is closed now");
        else {
            List<Product> items = itemsMap.getOrDefault(itemToAdd.getCategory(), new ArrayList<>());
            items.add(itemToAdd);
            itemsMap.put(itemToAdd.getCategory(), items);
        }
    }

    public List<Product> getItem(String category) {
        List<Product> itemsForResponse = new ArrayList<>();
        if (!(itemsMap.get(category) == null)) {
            itemsForResponse = itemsMap.get(category);
        } else {
            throw new OutOfStockException("No items in the category");
        }
        return itemsForResponse;
    }

    public List<Product> searchInItemsAndFilter(String categoryName, String itemName) {
        try {
            return itemsMap.get(categoryName).stream().filter(el -> el.getName().equals(itemName)).collect(Collectors.toList());
        } catch (NullPointerException e) {
            System.out.println("We don't have this category");
            return null;
        }
    }

    public List<Product> filterByPrice(String categoryName, double minPrice, double maxPrice) {
        try {
            return itemsMap.get(categoryName).stream().filter(el -> el.getPrice() >= minPrice && el.getPrice() <= maxPrice).collect(Collectors.toList());
        } catch (NullPointerException e) {
            System.out.println("We don't have this category");
            return null;
        }
    }

    public static void openInventory() {
        isInventoryOpen = true;
    }

    public static void closeInventory() {
        isInventoryOpen = false;
    }
}
