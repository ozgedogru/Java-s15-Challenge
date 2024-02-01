package library;

public class Education extends Book {

    public Education(int bookId, String title, Author author, double price, boolean isBorrowed) {
        super(bookId, title, author, price, Category.EDUCATION, isBorrowed);

    }

    @Override
    public String toString() {
        return "Education{" +

                "} " + super.toString();
    }
}