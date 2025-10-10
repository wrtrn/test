package expressJava.lesson3;

public class Library {

    private String bookTitle;
    protected String author;
    int year ;
    public String category;

    String getBookTitle(){
        return bookTitle;
    }

    String getAuthor(){
        return author;
    }

    int getYear(){
        return year;
    }

    String getCategory(){
        return category;
    }

    void setBookTitle(String bookTitle){
        this.bookTitle=bookTitle;
    }

    void setAuthor(String author){
        this.author=author;
    }

    void setYear(int year){
        this.year=year;
    }

    void setCategory(String category){
        this.category=category;
    }
}
