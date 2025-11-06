package learning.expressJava.complexTasks.inventoryService;

public class OutOfStockException extends RuntimeException {
    public OutOfStockException(String message) {
        super(message);
    }
}
