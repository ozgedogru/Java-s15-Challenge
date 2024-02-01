package library;

public class ScienceFiction extends Book {

    public ScienceFiction(int bookId, String title, Author author, double price, boolean isBorrowed) {
        super(bookId, title, author, price, Category.SCIENCE_FICTION, isBorrowed);

    }

}