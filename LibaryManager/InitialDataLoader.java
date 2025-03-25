import java.io.File;
import java.util.Arrays;
import java.util.List;


public class InitialDataLoader {
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

    // Metode sākotnējo datu ielādei CSV failos, ja tie ir tukši
    public static void loadInitialData() {
        File booksFile = new File(CSVHandler.BOOKS_FILE);
        File clientsFile = new File(CSVHandler.CLIENTS_FILE);

        // Ja fails ar grāmatām neeksistē vai ir tukšs, mēs rakstām sākotnējās grāmatas
        if (!booksFile.exists() || booksFile.length() == 0) {
            CSVHandler.saveBooksToCSV(initialBooks);
        }

        // Ja klienta fails neeksistē vai ir tukšs, mēs ierakstām sākotnējos klientus
        if (!clientsFile.exists() || clientsFile.length() == 0) {
            CSVHandler.saveClientsToCSV(initialClients);
        }
    }
}
