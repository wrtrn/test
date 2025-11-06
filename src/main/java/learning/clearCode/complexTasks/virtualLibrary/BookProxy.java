package learning.clearCode.complexTasks.virtualLibrary;

public class BookProxy {
    Book book;
    String description;
    String author;
    String title;

    public BookProxy(Book book) {
        this.book = book;
    }

    public String getContent() {
        if (title == null) {
            description = book.getDescription();
            author = book.getAuthor();
            title = book.getTitle();
        }
        return "Title: " + title + " Author: " + author + " Description: " + description;
    }
}
