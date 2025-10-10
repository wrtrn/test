package expressJava.lesson2;

public class Book {
    String title, author;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    void setTitle(String title) {
        this.title = title;
    }

    void setAuthor(String author) {
        this.author = author;
    }

    String getTitle() {
        return title;
    }

    String getAuthor() {
        return author;
    }

    void printInfo() {
        System.out.println(title + " " + author);
    }
}
