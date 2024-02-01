package library;

import java.util.Map;

public class Librarian extends Person implements ILibraryOperations {
    private LibrarySystem librarySystem;
    private String password;

    public Librarian(LibrarySystem librarySystem, String name, String password) {
        super(Identity.LIBRARIAN, name);
        this.librarySystem = librarySystem;
        this.password = "1234";

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean authenticate(String enteredPassword) {
        return this.password.equals(enteredPassword);
    }

    @Override
    public void addBook(Book book) {
        librarySystem.addBook(book);

        System.out.println("Book added to the library: " + book.getTitle());
    }

    @Override
    public void removeBook(int bookId) {
        librarySystem.removeBook(bookId);
        System.out.println("Book removed from the library with ID: " + bookId);
    }

    @Override
    public void searchBook(String keyword) {
        System.out.println("Searching for books with keyword: " + keyword);
        Map<Integer, Book> books = librarySystem.getBooks();

        boolean found = false;
        for (Book book : books.values()) {
            if (containsIgnoreCase(book.getTitle(), keyword)) {
                found = true;
                System.out.println(" - " + book.getTitle() + " by " + book.getAuthor().getName());
            }
        }

        if (!found) {
            System.out.println("No matching books found in the library with keyword: " + keyword);
        }
    }

    @Override
    public void addMember(Member member) {
        librarySystem.addMember(member);
        System.out.println("Member added to the library: " + member.getName());
    }

    @Override
    public void removeMember(int memberId) {
        librarySystem.removeMember(memberId);
        System.out.println("Member removed from the library with ID: " + memberId);
    }

    @Override
    public void verifyMember(int memberId) {
        Map<Integer, Member> members = librarySystem.getMembers();

        if (members.containsKey(memberId)) {
            Member member = members.get(memberId);
            System.out.println("Member verified: " + member.getName());
        } else {
            System.out.println("Member not found in the library with ID: " + memberId);
        }
    }

    public void lendBook(int memberId, int bookId) {
        Map<Integer, Member> members = librarySystem.getMembers();
        Map<Integer, Book> books = librarySystem.getBooks();

        if (members.containsKey(memberId) && books.containsKey(bookId)) {
            Member member = members.get(memberId);
            Book book = books.get(bookId);

            if (!book.isBorrowed()) {
                member.borrowBook(memberId, bookId);
                book.setBorrowed(true);
                book.setBorrower(member);

                generateInvoice(book);
            } else {
                System.out.println("Book is already borrowed by someone else.");
            }
        } else {
            System.out.println("Invalid member ID or book ID.");
        }
    }


    public void takeBackBook(int memberId, int bookId) {
        Map<Integer, Member> members = librarySystem.getMembers();
        Map<Integer, Book> books = librarySystem.getBooks();

        if (members.containsKey(memberId) && books.containsKey(bookId)) {
            Member member = members.get(memberId);
            Book book = books.get(bookId);

            if (book.isBorrowed() && member.getBorrowedBooks().containsKey(bookId)) {
                member.returnBook(bookId, memberId);
                book.setBorrowed(false);
                book.setBorrower(null);

                System.out.println("Book returned successfully!");

                double repayment;
                if ("student".equalsIgnoreCase(member.getType())) {
                    repayment = book.getPrice() * 0.8;
                } else {
                    repayment = book.getPrice();
                }
                System.out.println("Total Amount to be Paid: $" + repayment);

            } else {
                System.out.println("Book is not borrowed by the specified member.");
            }
        } else {
            System.out.println("Invalid member ID or book ID.");
        }
    }

    @Override
    public void generateInvoice(Book book) {
        if (book != null) {
            Invoice invoice = new Invoice(book);
            invoice.generateInvoice();
        } else {
            System.out.println("Book is null. Cannot generate invoice.");
        }
    }

    private boolean containsIgnoreCase(String text, String keyword) {
        return text.toLowerCase().contains(keyword.toLowerCase());
    }
}
