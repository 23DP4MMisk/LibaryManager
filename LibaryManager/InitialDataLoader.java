import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;



public class InitialDataLoader {
    
    private static List<Book> books;
    private static List<Client> clients;
    
    
    private static final List<Book> initialBooks = Arrays.asList(
        new Book("The Catcher in the Rye", "J.D. Salinger", 1951),
        new Book("1984", "George Orwell", 1949),
        new Book("To Kill a Mockingbird", "Harper Lee", 1960)
    );

    private static final List<Client> initialClients = Arrays.asList(
        new Client("John"),
        new Client("Jane"),
        new Client("Alice")
    );

    public static void loadInitialData() {
        File booksFile = new File(CSVHandler.BOOKS_FILE);
        File clientsFile = new File(CSVHandler.CLIENTS_FILE);
        File borrowedBooksFile = new File(CSVHandler.BORROWED_BOOKS_FILE);

        // Rakstiet sākotnējās grāmatas, ja faila trūkst vai tas ir tukšs
        if (!booksFile.exists() || booksFile.length() == 0) {
            CSVHandler.saveBooksToCSV(initialBooks);
        }

        // Ierakstiet sākotnējos klientus, ja faila trūkst vai tas ir tukšs
        if (!clientsFile.exists() || clientsFile.length() == 0) {
            CSVHandler.saveClientsToCSV(initialClients);
        }

        // Izveidojiet tukšu borrowed_books.csv failu, ja tas neeksistē
        if (!borrowedBooksFile.exists()) {
            try {
                borrowedBooksFile.createNewFile();
                System.out.println("Armored books file created: " + CSVHandler.BORROWED_BOOKS_FILE);
            } catch (IOException e) {
                System.out.println("Error creating armored books file: " + e.getMessage());
            }
        }

        // Notiek grāmatu un klientu ielāde no CSV
        books = CSVHandler.loadBooksFromCSV();
        clients = CSVHandler.loadClientsFromCSV();
  
        // Notiek rezervēto grāmatu ielāde
        Map<String, List<String>> borrowedBooks = CSVHandler.loadBorrowedBooks();
      
  
          for (Map.Entry<String, List<String>> entry : borrowedBooks.entrySet()) {
              String clientName = entry.getKey();
              Client client = Client.findClientByName(clients, clientName);
              if (client != null) {
                  for (String bookTitle : entry.getValue()) {
                      Book book = Book.findBookByTitle(books, bookTitle);
                      if (book != null) {
                          client.borrowBook(book); // Grāmatas pievienošana klientam
                          book.borrowBook(); // Atzīmējiet grāmatu kā uzņemtu
                         
                    }
                }
            }
        }

    }

    // Datu iegūšanas metodes
    public static List<Book> getBooks() {
        return books;
    }

    public static List<Client> getClients() {
        return clients;
    }
}   




