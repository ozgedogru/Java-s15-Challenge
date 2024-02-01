package library;

import java.util.List;

public interface IReaderOperations {
    Book searchBookById(int bookId);
    List<Book> searchBooksByTitle(String title);
    List<Book> searchBooksByAuthor(String authorName);
    void borrowBook(int memberId, int bookId);
    void returnBook(int memberId, int bookId);

}
