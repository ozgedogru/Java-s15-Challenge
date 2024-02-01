package library;

public class ScienceFiction extends Book {
    private String subgenre;

    public ScienceFiction(int bookId, String title, Author author, double price, boolean isBorrowed, String subgenre) {
        super(bookId, title, author, price, Category.SCIENCE_FICTION, isBorrowed);
        this.subgenre = subgenre;
    }

    public String getSubgenre() {
        return subgenre;
    }

    public void setSubgenre(String subgenre) {
        this.subgenre = subgenre;
    }

    @Override
    public String toString() {
        return "ScienceFiction{" +
                "subgenre='" + subgenre + '\'' +
                "} " + super.toString();
    }
}