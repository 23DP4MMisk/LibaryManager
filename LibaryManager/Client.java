import java.util.*;

public class Client {
    private String name;
    private Book borrowedBook;

    public Client(String InitilazatName){
      this.name = InitilazatName;
      this.borrowedBook = null;
    }

    public String getName(){
        return this.name;
    }

    public Book getBorrowedBook(){
        return borrowedBook;
    }
    

    public boolean borrowBook(Book book){
      if(book == null || book.isBorrowed()){
        System.out.println("Gramata nav piejama ");
        return false;
      }
      this.borrowedBook = book;
      book.setBorrowed(true);
      System.out.println(name + "gramatu " + book.bookName());
      return true;


    }

    
    
    public void borrowedBook(Book book){
        if (book != null && !book.isBorrowed()){
            borrowedBook = book;
            book.borrow();
        }
    }

    public void returnBook(){
        if (borrowedBook != null){
            borrowedBook.setBorrowed(false);
            System.out.println(name + "atdod gramatu: " + borrowedBook.bookName());
            borrowedBook = null;

        }else {
            System.out.println(name + "neizmantoja gramatu");
        }
    }

    @Override
    public String toString(){
        return String.format("Client: %s, Borrowed Book: %s", name, borrowedBook != null ? borrowedBook.bookName() : "None");
    }

    public static void registerClient(List <Client> clients, String name){
        clients.add(new Client(name));
    }

    public static void listClients(List<Client> clients) {
        System.out.println("\n======================================");
        System.out.println("Name client          |  Borrowed Book");
        System.out.println("\n======================================");
        clients.forEach(client ->{
            String borrowedBook = client.getBorrowedBook() != null ? client.getBorrowedBook().bookName() : "Nav";
            System.out.printf("%-20s | %-30s%n", client.getName(), borrowedBook);
        });
    }
    
}
