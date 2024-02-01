package library;

public class History extends Book {
    private String era;

    public History(int bookId, String title, Author author, double price, boolean isBorrowed, String era) {
        super(bookId, title, author, price, Category.HISTORY, isBorrowed);
        this.era = era;
    }
    public History(int bookId, String title, Author author, double price, boolean isBorrowed, Member borrower, String era) {
        super(bookId, title, author, price, Category.HISTORY, isBorrowed, borrower);
        this.era = era;
    }


    public String getEra() {
        return era;
    }

    public void setEra(String era) {
        this.era = era;
    }

    @Override
    public String toString() {
        return "History{" +
                "era='" + era + '\'' +
                "} " + super.toString();
    }
}