package main;

public class CommandPrinter {
    public static void printCommands() {
        System.out.printf("%-20s%-50s\n", ColorScheme.COMMAND_COLOR + "add" + ColorScheme.RESET, "- Add a new book");
        System.out.printf("%-20s%-50s\n", ColorScheme.COMMAND_COLOR + "remove" + ColorScheme.RESET, "- Remove a book");
        System.out.printf("%-20s%-50s\n", ColorScheme.COMMAND_COLOR + "find" + ColorScheme.RESET, "- Find a book");
        System.out.printf("%-20s%-50s\n", ColorScheme.COMMAND_COLOR + "list" + ColorScheme.RESET, "- List all books");
        System.out.printf("%-20s%-50s\n", ColorScheme.COMMAND_COLOR + "list_client" + ColorScheme.RESET, " - List all clients");
        System.out.printf("%-20s%-50s\n", ColorScheme.COMMAND_COLOR + "register" + ColorScheme.RESET, "- Register a new client");
        System.out.printf("%-20s%-50s\n", ColorScheme.COMMAND_COLOR + "remove_client" + ColorScheme.RESET, " - Delete client");
        System.out.printf("%-20s%-50s\n", ColorScheme.COMMAND_COLOR + "count" + ColorScheme.RESET, "- Count books");
        System.out.printf("%-20s%-50s\n", ColorScheme.COMMAND_COLOR + "sort_asc" + ColorScheme.RESET, "- Sort books by year (ascending)");
        System.out.printf("%-20s%-50s\n", ColorScheme.COMMAND_COLOR + "sort_desc" + ColorScheme.RESET, " - Sort books by year (descending)");
        System.out.printf("%-20s%-50s\n", ColorScheme.COMMAND_COLOR + "borrow" + ColorScheme.RESET, "- Borrow a book");
        System.out.printf("%-20s%-50s\n", ColorScheme.COMMAND_COLOR + "return" + ColorScheme.RESET, "- Return a borrowed book");
        System.out.printf("%-20s%-50s\n", ColorScheme.COMMAND_COLOR + "exit" + ColorScheme.RESET, "- Exit the program");


    }
}
