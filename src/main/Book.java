package main;

import java.util.List;

import java.util.Comparator;

public class Book {
    private String title;
    private String author;
    private int year;
    private boolean isBorrowed;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.isBorrowed = false;
    }

    public String getTitle(){ 
        return title; 
    }
    
    public String getAuthor(){ 
        return author; 
    }
    
    public int getYear(){ 
        return year; 
    }
    
    public boolean isBorrowed(){ 
        return isBorrowed; 
    }

    public void borrowSilently() {
        if (!isBorrowed) {
            isBorrowed = true;
        }
    }
    
    
    
    public void borrowBook() {
        if (!isBorrowed) {
            isBorrowed = true;
            System.out.println("Book borrowed: " + title);
        } else {
            System.out.println("The book is already borrowed.");
        }
    }
    
    public void returnBook() {
        if (isBorrowed) {
            isBorrowed = false;
            System.out.println("Book returned: " + title);
        } else {
            System.out.println("The book was not borrowed.");
        }
    }

    @Override
    public String toString(){
        return String.format("Title: %s, Author: %s, Year: %d, Borrowed: %b", title, author, year, isBorrowed);
    }

   
    // Comparator for sorting books by year (oldest to newest)
    public static Comparator<Book> sortByYearAsc = Comparator.comparingInt(Book::getYear);

    // Comparator for sorting books by year (newest to oldest)
    public static Comparator<Book> sortByYearDesc = (b1, b2) -> Integer.compare(b2.getYear(), b1.getYear());
    
   
    // Method to add a new book to the list
    public static void addBook(List<Book> books, String title, String author, int year) {
     books.add(new Book(title, author, year));
    }

   // Method to remove a book by title
    public static void removeBook(List<Book> books, String title) {
        boolean removed = books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
        if (removed) {
          CSVHandler.updateBooksCSV(books);  // Updating CSV
          System.out.println("Book \"" + title + "\" delete.");
        } else {
          System.out.println(ColorScheme.ERROR_COLOR + "Book \"" + title + "\" dont found." + ColorScheme.ERROR_RESET);
        }
    }

   // Method to find a book by title
   public static Book findBookByTitle(List<Book> books, String title) {
        return books.stream()
        .filter(book -> book.getTitle().equalsIgnoreCase(title))
        .findFirst()
        .orElse(null);
    }

   // Method to list all books
   public static void listBooks(List<Book> books) {
      books.forEach(System.out::println);
    }

   // Method to count total number of books
    public static void countBooks(List<Book> books) {
    System.out.println("Total books: " + books.size());
    }

   
    

  

    public static void countBorrowedBooks(List<Book> books) {
        long count = books.stream().filter(Book::isBorrowed).count();
        System.out.println(ColorScheme.TABLE_HEADER_COLOR + " Borrowed books: " + count + ColorScheme.TABLE_RESET);


    }

    // Method to sort books by year ascending
    public static void sortBooksByYearAsc(List<Book> books) {
        books.sort(Book.sortByYearAsc);
        listBooks(books);
    }

    // Method to sort books by year descending
    public static void sortBooksByYearDesc(List<Book> books) {
        books.sort(Book.sortByYearDesc);
        listBooks(books);
    }

   
}
