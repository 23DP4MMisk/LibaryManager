package main;

public class CommandPrinter {
    public static void printCommands() {
      
        System.out.println(ColorScheme.HEADER_COLOR + "\n=== COMMAND LIST ===" + ColorScheme.HEADER_RESET);
        System.out.println(ColorScheme.SUBHEADER_COLOR + "\n[BOOK] Book Management:" + ColorScheme.SUBHEADER_RESET);
        System.out.println("  add            - Add a new book");
        System.out.println("  remove         - Remove a book by title");
        System.out.println("  find           - Find a book by title");
        System.out.println("  list           - Display all books in a table");
        System.out.println("  count          - Count the books");
        System.out.println("  filter_year    - Filter books by year");
        System.out.println("  filter_borrow  - Filter by borrow status");
        System.out.println("  sort_asc       - Sort books by year in ascending order");
        System.out.println("  sort_desc      - Sort books by year in descending order");
        System.out.println(ColorScheme.SUBHEADER_COLOR + "\n[USER] Client Management:" + ColorScheme.SUBHEADER_RESET);
        System.out.println("  register       - Register a new client");
        System.out.println("  remove_client  - Remove a client");
        System.out.println("  list_client    - Display all clients");
        System.out.println(ColorScheme.SUBHEADER_COLOR + "\n[BOOK ACTION] Borrow and Return:" + ColorScheme.SUBHEADER_RESET);
        System.out.println("  borrow         - Client borrows a book");
        System.out.println("  return         - Client returns a book");
        System.out.println(ColorScheme.SUBHEADER_COLOR + "\n[UTILITY] Additional:" + ColorScheme.SUBHEADER_RESET);
        System.out.println("  exit           - Exit the program");
        System.out.println();
        
        
    }
}



