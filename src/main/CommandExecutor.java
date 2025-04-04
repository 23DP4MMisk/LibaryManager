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

            case "count":
                countBooks(books);
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
                System.out.println("Invalid command.");
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
            System.out.println("Book not found.");
        }
    }

   

    

    private static void registerClient(Scanner scanner, List<Client> clients) {
        System.out.print("Enter client name: ");
        String name = scanner.nextLine();
        Client.registerClient(clients, name);
        CSVHandler.saveClientsToCSV(clients);
    }

    private static void countBooks(List<Book> books) {
        Book.countBooks(books);
    }

  

   

    private static void borrowBook(Scanner scanner, List<Book> books, List<Client> clients) {
        System.out.print("Enter client name: ");
        String clientName = scanner.nextLine();
        Client client = Client.findClientByName(clients, clientName);
        if (client == null) {
            System.out.println("Client not found.");
            return;
        }

        System.out.print("Enter book title to borrow: ");
        String bookTitle = scanner.nextLine();
        Book book = Book.findBookByTitle(books, bookTitle);
        if (book != null) {
            client.borrowBook(book);
            CSVHandler.saveBorrowedBook(clientName, bookTitle);
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void returnBook(Scanner scanner, List<Book> books, List<Client> clients) {
        System.out.print("Enter client name: ");
        String clientName = scanner.nextLine();
        Client client = Client.findClientByName(clients, clientName);
        if (client == null) {
            System.out.println("Client not found.");
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
            System.out.println("Book not found.");
        }
    }
}


