import java.io.*;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


public class CSVHandler {
    public static final String BOOKS_FILE = "books.csv";
    public static final String CLIENTS_FILE = "clients.csv";

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
                Book book = new Book(data[0], data[1], Integer.parseInt(data[2])); // Grāmatas izveide
                if (Boolean.parseBoolean(data[3])) { // Pārbauda, ​​vai grāmata ir aizņemta
                    book.borrowBook();
                }
                books.add(book); // Pievienojiet grāmatu sarakstam
            }
        } catch (IOException e) {
            System.out.println("Error loading books from CSV.");
        }
        return books;
    }

    // Сохранение клиентов в CSV
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
}
