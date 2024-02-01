package library;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Member extends Person implements IReaderOperations {
    private LibrarySystem librarySystem;
    private int memberId;
    private String type;
    private String phoneNumber;
    private Map<Integer, Book> borrowedBooks;


    public Member(String name, int memberId, String type, String phoneNumber, LibrarySystem
            librarySystem) {
        super(Identity.MEMBER, name);
        this.memberId = memberId;
        this.type = type;
        this.phoneNumber = phoneNumber;
        this.borrowedBooks = new HashMap<>();
        this.librarySystem = librarySystem;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getType() {
        return type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public Map<Integer, Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setBorrowedBooks(Map<Integer, Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public Book searchBookById(int bookId) {
        return librarySystem.searchBookById(bookId);
    }
    public List<Book> searchBooksByTitle(String title) {
        return librarySystem.searchBooksByTitle(title);
    }
    public List<Book> searchBooksByAuthor(String authorName) {
        return librarySystem.searchBooksByAuthor(authorName);
    }

    public void borrowBook(int memberId, int bookId) {
        Book bookToBorrow = librarySystem.searchBookById(bookId);
        Member borrowingMember = librarySystem.findMemberById(memberId);

        if (bookToBorrow != null && borrowingMember != null) {
            if (!bookToBorrow.isBorrowed()) {
                if (borrowingMember.getBorrowedBooks().size() < 5) {
                    borrowingMember.getBorrowedBooks().put(bookToBorrow.getBookId(), bookToBorrow);
                    bookToBorrow.setBorrowed(true);
                    bookToBorrow.setBorrower(borrowingMember);
                    System.out.println("Book borrowed successfully by Member ID " + memberId + "!");
                } else {
                    System.out.println("You cannot borrow more than 5 books. Please return some books before borrowing a new one.");
                }
            } else {
                System.out.println("Book is already borrowed by someone else.");
            }
        } else {
            System.out.println("Book not found with ID: " + bookId + " or Member not found with ID: " + memberId);
        }
    }



    public void returnBook(int memberId, int bookId) {
        Member member = librarySystem.findMemberById(memberId);

        if (member != null) {
            Book bookToReturn = librarySystem.searchBookById(bookId);
            if (bookToReturn != null) {
                if (bookToReturn.isBorrowed() && member.getBorrowedBooks().containsKey(bookToReturn.getBookId())) {
                    member.getBorrowedBooks().remove(bookToReturn.getBookId());
                    bookToReturn.setBorrowed(false);
                    bookToReturn.setBorrower(null);
                    System.out.println("Book returned successfully!");
                } else {
                    System.out.println("Book is not borrowed by you.");
                }
            } else {
                System.out.println("Book not found with ID: " + bookId);
            }
        } else {
            System.out.println("Member not found with ID: " + memberId);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return memberId == member.memberId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", type='" + type + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", borrowedBooks=" + borrowedBooks +
                ", name='" + getName() + '\'' +
                '}';
    }


}
