package complexTasks;

import expressJava.complexTasks.inventoryService.InventoryService;
import expressJava.complexTasks.inventoryService.OutOfStockException;
import expressJava.complexTasks.inventoryService.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InventoryServiceTest {

    @Test
    public void addItemAndGetItemTest() {
        InventoryService inventoryService = new InventoryService();

        //when inventory is closed
        InventoryService.closeInventory();
        inventoryService.addItem(new Product("Brush", 10.0, "Home"));
        inventoryService.addItem(new Product("Brush2", 10.0, "Home3"));
        Assertions.assertEquals(0, inventoryService.getItemsMap().size());

        InventoryService.openInventory();

        inventoryService.addItem(new Product("Brush1", 10.0, "Home1"));
        inventoryService.addItem(new Product("Brush2", 21.0, "Home2"));
        inventoryService.addItem(new Product("Brush3", 22.0, "Home2"));
        inventoryService.addItem(new Product("Brush4", 23.0, "Home3"));
        inventoryService.addItem(new Product("Brush5", 24.0, "Home3"));
        inventoryService.addItem(new Product("Brush6", 25.0, "Home3"));
        inventoryService.addItem(new Product("Brush7", 26.0, "Home3"));
        inventoryService.addItem(new Product("Brush8", 27.0, "Home3"));

        Assertions.assertEquals(1, inventoryService.getItemsMap().get("Home1").size());
        Assertions.assertEquals(2, inventoryService.getItemsMap().get(("Home2")).size());
        Assertions.assertEquals("Brush4", inventoryService.getItemsMap().get(("Home3")).getFirst().getName());
        Assertions.assertEquals("Brush8", inventoryService.getItemsMap().get(("Home3")).getLast().getName());
        Assertions.assertEquals(3, inventoryService.getItemsMap().size());
    }

    @Test
    public void removeItemTest() {
        InventoryService inventoryService = new InventoryService();
        InventoryService.openInventory();

        inventoryService.addItem(new Product("Brush1", 10.0, "Home1"));
        inventoryService.addItem(new Product("Brush2", 21.0, "Home2"));
        inventoryService.addItem(new Product("Brush3", 22.0, "Home2"));
        inventoryService.addItem(new Product("Brush4", 23.0, "Home3"));
        inventoryService.addItem(new Product("Brush5", 24.0, "Home3"));
        inventoryService.addItem(new Product("Brush6", 25.0, "Home3"));
        inventoryService.addItem(new Product("Brush7", 26.0, "Home3"));
        inventoryService.addItem(new Product("Brush8", 27.0, "Home3"));

        inventoryService.removeItem("Home3", "Brush6");
        Assertions.assertEquals(4, inventoryService.getItemsMap().get("Home3").size());


        inventoryService.removeItem("Home3", "NEW");
        Assertions.assertEquals(4, inventoryService.getItemsMap().get("Home3").size());

        inventoryService.removeItem("Home1", "Brush1");
        Assertions.assertThrows(OutOfStockException.class, () -> inventoryService.removeItem("Home1", "Brush1"));
        Assertions.assertThrows(OutOfStockException.class, () -> inventoryService.removeItem("NEW", "Brush1"));
    }

    @Test
    public void searchAndFilterTest() {
        InventoryService inventoryService = new InventoryService();

        InventoryService.openInventory();

        inventoryService.addItem(new Product("Brush1", 10.0, "Home1"));
        inventoryService.addItem(new Product("Brush2", 21.0, "Home2"));
        inventoryService.addItem(new Product("Brush3", 22.0, "Home2"));
        inventoryService.addItem(new Product("Brush4", 23.0, "Home3"));
        inventoryService.addItem(new Product("Brush5", 24.0, "Home3"));
        inventoryService.addItem(new Product("Brush6", 25.0, "Home3"));
        inventoryService.addItem(new Product("Brush6", 25.0, "Home3"));
        inventoryService.addItem(new Product("Brush6", 25.0, "Home3"));
        inventoryService.addItem(new Product("Brush7", 26.0, "Home3"));
        inventoryService.addItem(new Product("Brush8", 27.0, "Home3"));

        inventoryService.searchInItemsAndFilter("Home3", "Brush6").size();
        Assertions.assertEquals(3, inventoryService.searchInItemsAndFilter("Home3", "Brush6").size());
        Assertions.assertEquals("Brush1", inventoryService.searchInItemsAndFilter("Home1", "Brush1").getFirst().getName());
        Assertions.assertEquals(0, inventoryService.searchInItemsAndFilter("Home1", "NEW ITEM").size());
        Assertions.assertNull(inventoryService.searchInItemsAndFilter("NEW", "Brush1"));
    }

    @Test
    public void filterByPriceTest() {
        InventoryService inventoryService = new InventoryService();

        InventoryService.openInventory();

        inventoryService.addItem(new Product("Brush1", 10.0, "Home1"));
        inventoryService.addItem(new Product("Brush2", 21.0, "Home2"));
        inventoryService.addItem(new Product("Brush3", 22.0, "Home2"));
        inventoryService.addItem(new Product("Brush4", 23.0, "Home3"));
        inventoryService.addItem(new Product("Brush5", 24.0, "Home3"));
        inventoryService.addItem(new Product("Brush6", 25.0, "Home3"));
        inventoryService.addItem(new Product("Brush7", 26.0, "Home3"));
        inventoryService.addItem(new Product("Brush8", 25.5, "Home3"));

        Assertions.assertEquals(3, inventoryService.filterByPrice("Home3", 25, 26).size());
        Assertions.assertEquals(0, inventoryService.filterByPrice("Home3", 0, 1).size());
        Assertions.assertNull(inventoryService.filterByPrice("New", 0, 1));
    }
}
