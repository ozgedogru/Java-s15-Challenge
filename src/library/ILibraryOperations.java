package library;

public interface ILibraryOperations {
    void addBook(Book book);
    void removeBook(int bookId);
    void searchBook(String keyword);
    void addMember(Member member);
    void removeMember(int memberId);
    void verifyMember(int memberId);
    void lendBook(int memberId, int bookId);
    void takeBackBook(int memberId, int bookId);
    void generateInvoice(Book book);
}
