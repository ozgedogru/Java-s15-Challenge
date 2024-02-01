package library;

public class Education extends Book {
    private String subject;

    public Education(int bookId, String title, Author author, double price, boolean isBorrowed, String subject) {
        super(bookId, title, author, price, Category.EDUCATION, isBorrowed);
        this.subject = subject;
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Education{" +
                "subject='" + subject + '\'' +
                "} " + super.toString();
    }
}