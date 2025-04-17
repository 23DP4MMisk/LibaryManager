package main;

import java.util.List;
import java.util.Scanner;


public class CommandExecutor {
    public static void executeCommand(String command, Scanner scanner, List<Book> books, List<Client> clients) {
        switch (command) {
            case "add":
                addBook(scanner, books);
                break;

            case "remove":
                removeBook(scanner, books);
                break;

            case "find":
                findBook(scanner, books);
                break;

            case "list":
                TableFormatter.printBooksTable(books);;
                break;

            case "list_client":
                TableFormatter.printClientsTable(clients);
                break;

            case "register":
                registerClient(scanner, clients);
                break;

            case "remove_client":
                removeClient(clients, books, scanner);
                break;

            case "count":
                countBooks(books);
                break;
            
            case "filter_year":
               filterBooksByYear(scanner, books);
               break;

            case "filter_borrow":
               filterBooksByBorrowedStatus(scanner, books);
               break;

            case "sort_asc":
                TableFormatter.printSortedBooksTable(books, Book.sortByYearAsc);
                break;

            case "sort_desc":
                TableFormatter.printSortedBooksTable(books, Book.sortByYearDesc);
                break;

            case "borrow":
                borrowBook(scanner, books, clients);
                break;

            case "return":
                returnBook(scanner, books, clients);
                break;

            case "exit":
                System.out.println("Goodbye!");
                System.exit(0);
                break;

            default:
                System.out.println(ColorScheme.ERROR_COLOR + "Invalid command." + ColorScheme.ERROR_RESET);
        }
    }

    private static void addBook(Scanner scanner, List<Book> books) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        Book.addBook(books, title, author, year);
        CSVHandler.saveBooksToCSV(books);
    }

    private static void removeBook(Scanner scanner, List<Book> books) {
        System.out.print("Enter book title to remove: ");
        String title = scanner.nextLine();
        Book.removeBook(books, title);
    }

    private static void findBook(Scanner scanner, List<Book> books) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        Book book = Book.findBookByTitle(books, title);
        if (book != null) {
            System.out.println(book);
        } else {
            System.out.println(ColorScheme.ERROR_COLOR + "Book not found." + ColorScheme.ERROR_RESET);
        }
    }

   

    

    private static void registerClient(Scanner scanner, List<Client> clients) {
        System.out.print("Enter client name: ");
        String name = scanner.nextLine();
        Client.registerClient(clients, name);
        CSVHandler.saveClientsToCSV(clients);
    }

    public static void removeClient(List<Client> clients, List<Book> books, Scanner scanner) {
        System.out.print("Enter client name to remove: ");
        String name = scanner.nextLine();
    
        Client client = Client.findClientByName(clients, name);
        if (client == null) {
            System.out.println(ColorScheme.ERROR_COLOR + "Client not found." + ColorScheme.ERROR_RESET);
            return;
        }
    
        // Parbaudam vai visi gramatas klients nododa biblioteka
        if (!client.getBorrowedBook().isEmpty()) {
            System.out.println(ColorScheme.ERROR_COLOR + "Client cannot be removed. They have borrowed books that must be returned first." + ColorScheme.ERROR_RESET);
            return;
        }
    
        clients.remove(client);
        CSVHandler.updateClientsCSV(clients); // Apdejtojam clients.csv
        System.out.println("Client removed successfully.");
    }

    private static void countBooks(List<Book> books) {
        Book.countBooks(books);
    }

  

   

    private static void borrowBook(Scanner scanner, List<Book> books, List<Client> clients) {
        System.out.print("Enter client name: ");
        String clientName = scanner.nextLine();
        Client client = Client.findClientByName(clients, clientName);
        if (client == null) {
            System.out.println(ColorScheme.ERROR_COLOR + "Client not found." + ColorScheme.ERROR_RESET);
            return;
        }

        System.out.print("Enter book title to borrow: ");
        String bookTitle = scanner.nextLine();
        Book book = Book.findBookByTitle(books, bookTitle);
        if (book != null) {
            client.borrowBook(book);
            CSVHandler.saveBorrowedBook(clientName, bookTitle);
        } else {
            System.out.println(ColorScheme.ERROR_COLOR + "Book not found." + ColorScheme.ERROR_RESET);
        }
    }

    private static void returnBook(Scanner scanner, List<Book> books, List<Client> clients) {
        System.out.print("Enter client name: ");
        String clientName = scanner.nextLine();
        Client client = Client.findClientByName(clients, clientName);
        if (client == null) {
            System.out.println(ColorScheme.ERROR_COLOR + "Client not found." + ColorScheme.ERROR_RESET);
            return;
        }

        if (client.getBorrowedBook().isEmpty()) {
            System.out.println(ColorScheme.ERROR_COLOR + "This client hasn't borrowed any books." + ColorScheme.ERROR_RESET);
            return;
        }

        System.out.print("Enter book title to return: ");
        String bookTitle = scanner.nextLine();
        Book book = Book.findBookByTitle(books, bookTitle);
        if (book != null) {
            client.returnBook(book);
            CSVHandler.saveClientsToCSV(clients);
            CSVHandler.removeBorrowedBook(clientName, bookTitle);
        } else {
            System.out.println(ColorScheme.ERROR_COLOR + "Book not found." + ColorScheme.ERROR_RESET);
        }
    }

    private static void filterBooksByYear(Scanner scanner, List<Book> books) {
        System.out.print("Enter the year to filter by: ");
        int year = scanner.nextInt();
        List<Book> filteredBooks = Book.filterBooksByYear(books, year);
        if (filteredBooks.isEmpty()) {
            System.out.println(ColorScheme.ERROR_COLOR + "No books found for the year " + year + "." + ColorScheme.ERROR_RESET);
        } else {
            TableFormatter.printBooksTable(filteredBooks);  // Display filtered books in table format
        }
    }

    private static void filterBooksByBorrowedStatus(Scanner scanner, List<Book> books) {
        System.out.print("Enter borrowed status (true for borrowed, false for available): ");
        boolean isBorrowed = scanner.nextBoolean();
        List<Book> filteredBooks = Book.filterBooksByBorrowedStatus(books, isBorrowed);
        if (filteredBooks.isEmpty()) {
            System.out.println(ColorScheme.ERROR_COLOR + "No books found with borrowed status " + isBorrowed + "." + ColorScheme.ERROR_RESET);
        } else {
            TableFormatter.printBooksTable(filteredBooks);  // Display filtered books in table format
        }
    }
}


