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
    
    public void borrowedBook(Book book){
        if (book != null && !book.isBorrowed()){
            borrowedBook = book;
            book.borrow();
        }
    }

    public void returnBook(){
        if (borrowedBook != null){
            borrowedBook.returnBook();
            borrowedBook = null;
        }
    }
    
}
