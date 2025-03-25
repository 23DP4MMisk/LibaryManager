import java.util.List;
import java.util.Scanner;


public class LibaryManager {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Scanner scanner = new Scanner(System.in);
        
        InitialDataLoader.loadInitialData();

        
        
        List<Book> books = CSVHandler.loadBooksFromCSV();
        List<Client> clients = CSVHandler.loadClientsFromCSV();

        ASCIIAnimation.printWelcomeMessage();

        while (true) {
            System.out.println("\nAvailable commands:");
            CommandPrinter.printCommands();

            System.out.print("\n" + ColorScheme.COMMAND_COLOR + " Enter command: " + ColorScheme.RESET + " ");
            String command = scanner.nextLine().trim().toLowerCase();

            CommandExecutor.executeCommand(command, scanner, books, clients);
        }
    }
}

