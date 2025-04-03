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

    
    public void borrowBook(Book book) {
        if (!book.isBorrowed()) {
            borrowedBooks.add(book);
            book.borrowBook();
        } else {
            System.out.println("The book is already borrowed by another client.");
        }
    }
    
    
    
    
    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.returnBook();
        } else {
            System.out.println("You didn't borrow this book.");
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
