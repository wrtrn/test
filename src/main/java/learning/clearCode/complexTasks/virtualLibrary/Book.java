package learning.clearCode.complexTasks.virtualLibrary;

public class Book {
    private String title;
    private String author;
    private String description;

    public Book(BookBuilder builder) {
        this.title = builder.getTitle();
        this.author = builder.getAuthor();
        this.description = builder.getDescription();
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }
}
