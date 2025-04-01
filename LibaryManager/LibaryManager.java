import java.util.List;
import java.util.Scanner;


public class LibaryManager {

    // Command to clear all caches:
    // In VSCode press "F1", then type a command: Clean Java Language Server Workspace

    
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Scanner scanner = new Scanner(System.in);
        
        // Notiek datu ielāde
        InitialDataLoader.loadInitialData();

        // Mēs saņemam lejupielādētās grāmatas un klientus
        List<Book> books = InitialDataLoader.getBooks();
        List<Client> clients = InitialDataLoader.getClients();

       

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

