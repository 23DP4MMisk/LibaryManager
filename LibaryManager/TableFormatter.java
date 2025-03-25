import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;



public class TableFormatter {
    private static final String RESET = "\033[0m";
    private static final String HEADER_COLOR = "\033[1;34m"; // Zils
    private static final String ROW_COLOR = "\033[0;32m"; // Zaļš
    private static final String BORROWED_COLOR = "\033[0;31m"; // Sarkans (ja grāmata ir aizņemta)

    // Метод для вывода таблицы книг
    public static void printBooksTable(List<Book> books) {
        System.out.println(HEADER_COLOR + "----------------------------------------------------------------------------" + RESET);
        System.out.printf(HEADER_COLOR + "| %-30s | %-20s | %-5s | %-8s |%n", "Title", "Author", "Year", "Borrowed");
        System.out.println(HEADER_COLOR + "----------------------------------------------------------------------------" + RESET);
        for (Book book : books) {
            // Saglabājiet rindu apmaļu krāsu pēc krāsas maiņas uz Yes\No
            String borrowedStatus = book.isBorrowed() ? BORROWED_COLOR + "YES     " + RESET + ROW_COLOR : " NO ";
            System.out.printf(ROW_COLOR + "| %-30s | %-20s | %-5d | %-8s |%n", 
                book.getTitle(), book.getAuthor(), book.getYear(), borrowedStatus);
        }
        System.out.println(HEADER_COLOR + "----------------------------------------------------------------------------" + RESET);
    }

    // Sašķiroto grāmatu izvadīšanas metode
    public static void printSortedBooksTable(List<Book> books, Comparator<Book> comparator) {
        books.sort(comparator); // Grāmatu šķirošana pirms izdošanas
        
        System.out.println(HEADER_COLOR + "----------------------------------------------------------------------------" + RESET);
        System.out.printf(HEADER_COLOR + "| %-30s | %-20s | %-5s | %-8s |%n", "Title", "Author", "Year", "Borrowed");
        System.out.println(HEADER_COLOR + "----------------------------------------------------------------------------" + RESET);
        for (Book book : books) {
            // Saglabājiet rindu apmaļu krāsu pēc krāsas maiņas uz Yes\No
            String borrowedStatus = book.isBorrowed() ? BORROWED_COLOR + "YES     " + RESET + ROW_COLOR : " NO ";
            System.out.printf(ROW_COLOR + "| %-30s | %-20s | %-5d | %-8s |%n", 
                book.getTitle(), book.getAuthor(), book.getYear(), borrowedStatus);
        }
        System.out.println(HEADER_COLOR + "----------------------------------------------------------------------------" + RESET);
    }

    
    // Klientu tabulas izvadīšanas metode
    public static void printClientsTable(List<Client> clients) {
    // Inicializējiet klienta un grāmatu maksimālos garumus
    int maxClientNameLength = "Client Name".length();
    int maxBooksLength = "Borrowed Books".length();

    // Atrodiet klienta vārda maksimālo garumu un grāmatu sarakstu
    for (Client client : clients) {
        maxClientNameLength = Math.max(maxClientNameLength, client.getName().length());

        // Veidojot līniju ar grāmatu nosaukumiem
        String borrowedBooksList = client.getBorrowedBook().stream()
            .map(Book::getTitle)
            .collect(Collectors.joining(", "));

        maxBooksLength = Math.max(maxBooksLength, borrowedBooksList.length());
    }

    // Formāts tabulas attēlošanai
    String headerFormat = "| %-"+maxClientNameLength+"s | %-"+maxBooksLength+"s |";
    String separator = "-".repeat(maxClientNameLength + maxBooksLength + 7);

    // Tabulas drukāšana
    System.out.println(HEADER_COLOR + separator + RESET);
    System.out.printf(HEADER_COLOR + headerFormat + "%n", "Client Name", "Borrowed Books");
    System.out.println(HEADER_COLOR + separator + RESET);

    // Mēs šķirojam klientus un parādām viņu datus
    for (Client client : clients) {
        String borrowedBooksList = client.getBorrowedBook().stream()
            .map(Book::getTitle)
            .collect(Collectors.joining(", "));

        System.out.printf(ROW_COLOR + headerFormat + "%n" + RESET, client.getName(), borrowedBooksList.isEmpty() ? "None" : borrowedBooksList);
    }

     System.out.println(HEADER_COLOR + separator + RESET);
    }
}
