package library;

public class History extends Book {

    public History(int bookId, String title, Author author, double price, boolean isBorrowed) {
        super(bookId, title, author, price, Category.HISTORY, isBorrowed);
    }


}