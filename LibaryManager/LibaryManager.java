
import java.util.*;


public class LibaryManager {
  public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(System.in);
    List<Book> books = new ArrayList<>();
    List<Client> clients = new ArrayList<>();

    if(books.isEmpty()){
      Book.addBook(books, "1984", "George Orwell", 1949);
      Book.addBook(books, "To Kill a Nockingbird", "Harper Lee", 1960);
      Book.addBook(books, "The Catcher in the Rye", "J.D Salinger", 1951);
    }


    while(true){
    System.out.println("\nAvailable comands: add, remove, find, list, list_client, register, count, borrowed, exit");
    String command = scanner.nextLine();

    switch(command){
    case "add":
      System.out.println("Enter book name: ");
      String name = scanner.nextLine();
      System.out.println("Enter book author: ");
      String author = scanner.nextLine();
      System.out.println("Enter book year");
      int year = Integer.parseInt(scanner.nextLine());
      Book.addBook(books, name, author, year);
      break;
    case "remove":
      System.out.println("Enter book name to remove: ");
      name = scanner.nextLine();
      Book.removeBook(books, name);
      break;
    case "find":
      System.out.println("Enter book name to find");
      name = scanner.nextLine();
      Book foundBook = Book.findBook(books, name);
      if(foundBook != null){
        System.out.println(foundBook);
      }else {
        System.out.println("Book not found");
      }
      break;
    case "list":
      Book.listBooks(books);
      break;
    case "exit":
      System.out.println("Exiting...");
      return;


    }}
       
  }
}
