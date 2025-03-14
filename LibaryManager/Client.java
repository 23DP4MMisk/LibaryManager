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
    
    
    
}
