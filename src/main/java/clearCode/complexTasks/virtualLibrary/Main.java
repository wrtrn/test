package clearCode.complexTasks.virtualLibrary;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();

// Инициализация BookBuilder для создания книги
        BookBuilder builder = new BookBuilder();
        Book book1 = builder
                .setTitle("War and Peace")
                .setAuthor("Leo Tolstoy")
                .setDescription("A historical novel about the Napoleonic Wars.")
                .build();

        Book book2 = builder
                .setTitle("Hello")
                .setAuthor("Ivan Petrov")
                .setDescription("Interesting book")
                .build();

        books.add(book1);
        books.add(book2);

        BookCatalog catalog = new BookCatalog(books);

        catalog.checkCatalog();
        catalog.readBook("Hello");
        catalog.loadForOfflineReading("War and Peace");
    }
}
