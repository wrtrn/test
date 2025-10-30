package clearCode.complexTasks.virtualLibrary;

import java.util.List;

public class BookCatalog {
    List<Book> booksList;

    public BookCatalog(List<Book> booksList) {
        this.booksList = booksList;
    }

    public void checkCatalog() {
        for (Book book : booksList)
            System.out.println(book.getTitle());
    }

    public void readBook(String bookName) {
        Book bookForReading = findBookByName(bookName);
        BookProxy proxy = new BookProxy(bookForReading);
        String content = proxy.getContent();
        System.out.println("Content of the book: " + content);
    }

    public void loadForOfflineReading(String bookName) {
        Book bookForDownloading = findBookByName(bookName);
        BookProxy proxy = new BookProxy(bookForDownloading);
        System.out.println("Downloading the book " + proxy.book.getTitle());
    }

    private Book findBookByName(String bookName) {
        return booksList.stream().filter(el -> el.getTitle().equals(bookName)).findFirst().orElseThrow(() -> new RuntimeException("We didn't manage to find this book"));
    }
}
