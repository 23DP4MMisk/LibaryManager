package main;

import java.io.*;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





public class CSVHandler {
    public static final String BOOKS_FILE = "books.csv";
    public static final String CLIENTS_FILE = "clients.csv";
    public static final String BORROWED_BOOKS_FILE = "data/borrowed_books.csv";

    // Grāmatas tiek saglabātas CSV failā
    public static void saveBooksToCSV(List<Book> books) {
        try (BufferedWriter writer = Helper.getWriter(BOOKS_FILE, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            for (Book book : books) {
                writer.write(book.getTitle() + "," + book.getAuthor() + "," + book.getYear() + "," + book.isBorrowed());
                writer.newLine();  // Rindas pārtraukums pēc katra grāmatas ieraksta
            }
        } catch (IOException e) {
            e.printStackTrace();  // Drukājiet kļūdu, ja tāda rodas
        }
    }

    // Grāmatu lejupielāde no CSV
    public static List<Book> loadBooksFromCSV() {
        List<Book> books = new ArrayList<>();
        try (BufferedReader reader = Helper.getReader(BOOKS_FILE)) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(","); // Sadaliet virkni ar komatu
            if (data.length >= 4) { // Pārbauda datu pietiekamību
                try {
                    // Gada pārvēršana skaitļos
                    int year = Integer.parseInt(data[2]);
                    // Pārvērtiet grāmatas statusu uz Būla vērtību
                    boolean isBorrowed = Boolean.parseBoolean(data[3]);

                    // Grāmatas izveide
                    Book book = new Book(data[0], data[1], year);
                    if (isBorrowed) {
                        book.borrowBook(); // Ja grāmata ir paņemta, atzīmējam kā paņemtu
                    }
                    books.add(book); // Grāmatas pievienošana sarakstam
                } catch (NumberFormatException e) {
                    System.out.println("Data conversion error: " + line);
                }
            } else {
                System.out.println("Invalid string format: " + line);
            }
        }
        } catch (IOException e) {
           System.out.println("Error loading books from CSV.");
        }
        return books;
        
    }

    // Pēc grāmatas dzēšanas pārraksta CSV
    public static void updateBooksCSV(List<Book> books) {
        try (BufferedWriter writer = Helper.getWriter(BOOKS_FILE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE)) {
           for (Book book : books) {
             writer.write(book.getTitle() + "," + book.getAuthor() + "," + book.getYear() + "," + book.isBorrowed());
             writer.newLine();
            }
        } catch (IOException e) {
          System.out.println("Error updating books.csv: " + e.getMessage());
        }
    }

    public static void updateClientsCSV(List<Client> clients) {
        try (BufferedWriter writer = Helper.getWriter(CLIENTS_FILE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE)) {
            for (Client client : clients) {
                writer.write(client.getName());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error updating clients.csv: " + e.getMessage());
        }
    }

    // Klientu saglabāšana CSV formātā
    public static void saveClientsToCSV(List<Client> clients) {
        try (BufferedWriter writer = Helper.getWriter(CLIENTS_FILE, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            for (Client client : clients) {
                writer.write(client.getName()); // Mēs pierakstām klienta vārdu
                writer.newLine(); // Rindas pārtraukums pēc katra klienta ieraksta
            }
        } catch (IOException e) {
            e.printStackTrace(); // Drukājiet kļūdu, ja tāda rodas
        }
    }

    // Notiek klientu ielāde no CSV
    public static List<Client> loadClientsFromCSV() {
        List<Client> clients = new ArrayList<>();
        try (BufferedReader reader = Helper.getReader(CLIENTS_FILE)) {
            String line;
            while ((line = reader.readLine()) != null) {
                clients.add(new Client(line)); // Izveidojiet klientu no virknes un pievienojiet to sarakstam
            }
        } catch (IOException e) {
            System.out.println("Error loading clients from CSV.");
        }
        return clients;
    }

    public static void saveBorrowedBook(String clientName, String bookTitle) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BORROWED_BOOKS_FILE, true))) {
            writer.write(clientName + "," + bookTitle);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to borrowed_books.csv: " + e.getMessage());
        }
    }

    public static void removeBorrowedBook(String clientName, String bookTitle) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(BORROWED_BOOKS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.equals(clientName + "," + bookTitle)) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing to borrowed_books.csv: " + e.getMessage());
        }
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BORROWED_BOOKS_FILE))) {
            for (String l : lines) {
                writer.write(l);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error rewriting borrowed_books.csv: " + e.getMessage());
        }
    }

    public static Map<String, List<String>> loadBorrowedBooks() {
    Map<String, List<String>> borrowedBooks = new HashMap<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(BORROWED_BOOKS_FILE))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length == 2) {
                borrowedBooks.computeIfAbsent(data[0], k -> new ArrayList<>()).add(data[1]);
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading borrowed_books.csv: " + e.getMessage());
    }
    return borrowedBooks;
}

}