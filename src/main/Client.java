package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    private String name;
    private List<Book> borrowedBooks;

    public Client(String name) {
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName(){ 
        return name;
    }
    public List<Book> getBorrowedBook(){ 
        return borrowedBooks; 
    }

    // Metods lai neparadas teksts kadu gramatu paņem no bibliotekas
    public void borrowSilently(Book book) {
        if (!borrowedBooks.contains(book)) {
            borrowedBooks.add(book);
            book.borrowSilently(); 
        }
    }

    
    public void borrowBook(Book book) {
        if (!book.isBorrowed()) {
            borrowedBooks.add(book);
            book.borrowBook();
        } else {
            System.out.println(ColorScheme.ERROR_COLOR + "The book is already borrowed by another client." + ColorScheme.ERROR_RESET);
        }
    }
    
    
    
    
    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.returnBook();
        } else {
            System.out.println(ColorScheme.ERROR_COLOR + "You didn't borrow this book." + ColorScheme.ERROR_RESET);
        }
    }

    @Override
    public String toString() {
        return name + " (Borrowed books: " + borrowedBooks.size() + ")";
    }

    public static void registerClient(Scanner scanner, List<Client> clients) {
        System.out.print(ColorScheme.REGISTER_COLOR + " Enter client name: " + ColorScheme.RESET);
        String name = scanner.nextLine();
        clients.add(new Client(name));
        CSVHandler.saveClientsToCSV(clients);
    }

    public static void listClients(List<Client> clients) {
        TableFormatter.printClientsTable(clients);
    }

    // Method to register a new client
    public static void registerClient(List<Client> clients, String name) {
        clients.add(new Client(name));
    }

    public static Client findClientByName(List<Client> clients, String name) {
        return clients.stream().filter(client -> client.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

   
}
