package library;

public class Book {
    private int bookId;
    private String title;
    private Author author;
    private double price;
    private Category category;
    private boolean isBorrowed;
    private Member borrower;

    public Book(int bookId, String title, Author author, double price, Category category, boolean isBorrowed) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.price = price;
        this.category = category;
        this.isBorrowed = isBorrowed;
        this.borrower = null;

    }
    public Book(int bookId, String title, Author author, double price, Category category, boolean isBorrowed, Member borrower) {
        this(bookId, title, author, price, category, isBorrowed);
        this.borrower = borrower;
    }

    public int getBookId() {
        return bookId;
    }
    public String getTitle() {
        return title;
    }
    public Author getAuthor() {
        return author;
    }
    public double getPrice() {
        return price;
    }
    public Member getBorrower() {
        return borrower;
    }
    public boolean isBorrowed() {
        return isBorrowed;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public void setBorrower(Member borrower) {
        this.borrower = borrower;
    }

    public void updateStatus(boolean newStatus) {
        isBorrowed = newStatus;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Book book = (Book) obj;
        return bookId == book.bookId;
    }

    @Override
    public int hashCode() {
        return bookId;
    }

    @Override
    public String toString() {
        String authorName = (author != null) ? author.getName() : "Unknown Author";
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author=" + authorName +
                ", price=" + price +
                ", category=" + category +
                ", isBorrowed=" + (isBorrowed ? " borrowed" : " not borrowed") +
                ", borrower= " + (borrower != null ? borrower.getName() : "None") +
                '}';
    }

}
