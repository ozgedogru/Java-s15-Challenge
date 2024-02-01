package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibrarySystem {
    private Map<Integer, Book> books;
    private Map<Integer, Member> members;
    private Map<Integer, Author> authors;

    public LibrarySystem() {
        this.books = new HashMap<>();
        this.members = new HashMap<>();
        this.authors = new HashMap<>();
    }



    public Map<Integer, Book> getBooks() {
        return books;
    }
    public Map<Integer, Member> getMembers() {
        return members;
    }

    public void addBook(Book book) {
        books.put(book.getBookId(), book);
        Author author = book.getAuthor();
        if (author != null) {
            author.getWrittenBooks().put(book.getBookId(), book);
        }
    }
    public void removeBook(int bookId) {
        books.remove(bookId);
    }
    public void addMember(Member member) {
        members.put(member.getMemberId(), member);
    }
    public void removeMember(int memberId) {
        members.remove(memberId);
    }
    public void addAuthor(Author author) {
        authors.put(author.getAuthorId(), author);
    }
    public void removeAuthor(int authorId) {
        authors.remove(authorId);
    }
    public Author getAuthor(int authorId) {
        return authors.get(authorId);
    }

    public Book searchBookById(int bookId) {
        if (books.containsKey(bookId)) {
            return books.get(bookId);
        } else {
            System.out.println("Book not found with ID: " + bookId);
            return null;
        }
    }

    public List<Book> searchBooksByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                result.add(book);
            }
        }
        if (result.isEmpty()) {
            System.out.println("No books found with title: " + title);
        }
        return result;
    }

    public List<Book> searchBooksByAuthor(String authorName) {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getAuthor().getName().equalsIgnoreCase(authorName)) {
                result.add(book);
            }
        }
        if (result.isEmpty()) {
            System.out.println("No books found with author: " + authorName);
        }
        return result;
    }

    public static Member findMemberByName(LibrarySystem librarySystem, String memberName) {
        for (Member member : librarySystem.getMembers().values()) {
            if (member.getName().equalsIgnoreCase(memberName)) {
                return member;
            }
        }
        return null;
    }
    public Member findMemberById(int memberId) {
        return members.get(memberId);
    }

}
