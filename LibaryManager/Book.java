import java.util.*;

public class Book {
    private String name;
    private String author;
    private int year;
    private boolean busy;

    public Book(String InitilazationName, String InitilazationAuthor, int Initilazationyear){
        this.name = InitilazationName;
        this.author = InitilazationAuthor;
        this.year = Initilazationyear;
        this.busy = false;
    }

    public String bookName(){
        return this.name;
    }

    public String authorName(){
        return this.author;
    }

    public int bookYear(){
        return this.year;
    }

    public boolean isBorrowed(){
        return this.busy;
    }

    public void setBorrowed(boolean borrowed){
        this.busy = borrowed; 
    }
    
    
    public void borrow(){
        this.busy = true;
    }

    public void returnBook(){
        this.busy = false;
    }

    @Override
    public String toString(){
        return String.format("Title: %s, Author: %s, Year: %d, Borrowed: %b", this.name, this.author, this.year, this.busy);
    }

    public static void addBook(List<Book> books, String InitilazationName, String InitilazationAuthor, int Initilazationyear){
        books.add(new Book(InitilazationName, InitilazationAuthor, Initilazationyear));
    }

    public static void removeBook(List<Book> books, String InitilazationName){
        books.removeIf(book -> book.bookName().equalsIgnoreCase(InitilazationName));
    }

    public static Book findBook(List<Book> books, String InitilazationName){
        return books.stream()
        .filter(book -> book.bookName().equalsIgnoreCase(InitilazationName))
        .findFirst()
        .orElse(null);

    }

    public static void listBooks(List<Book> books){
       
        
        
        
        System.out.println("\n==============================================================");
        System.out.println("       Author     |       Book name           |     Book year ");
        System.out.println("===============================================================");
        books.forEach(book -> {
            System.out.printf("%-20s | %-30s | %-4d%n", book.authorName(), book.bookName(), book.bookYear());
        });
    }

    public static void countBooks(List<Book> books){
        System.out.println("Vispar gramatu biblioteka: " + books.size());
    }

    public static void countBorrowedBook(List<Book>books){
        long count = books.stream().filter(Book::isBorrowed).count();
        System.out.println("Gramatas aizņemtas skaits: " + count);
    }

    
}
