package library;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static library.LibrarySystem.findMemberByName;

public class Main {
    public static void main(String[] args) {
        LibrarySystem librarySystem = new LibrarySystem();
        Librarian librarian = new Librarian(librarySystem, "Ahmet", "1234");

        Author author1 = new Author(Identity.AUTHOR, "Lorem Ipsum", 1, null);
        librarySystem.addAuthor(author1);

        ScienceFiction book1 = new ScienceFiction(104, "The Theory of Everything", author1, 34.99, false);
        History book2 = new History(105, "World History: A Brief Overview", author1, 39.99,false);
        Book book3 = new Education(106, "Introduction to Java", author1, 19.99, false);
        Book book4 = new ScienceFiction(107, "Artificial Intelligence: Concepts and Techniques", author1, 49.99, false);
        Book book5 = new History(108, "Ancient Civilizations", author1, 29.99, false);
        Book book6 = new Education(109, "Data Structures and Algorithms in Python", author1, 44.99, false);


        librarySystem.addBook(book1);
        librarySystem.addBook(book2);
        librarySystem.addBook(book3);
        librarySystem.addBook(book4);
        librarySystem.addBook(book5);
        librarySystem.addBook(book6);

        Member member1 = new Member("Alice", 1, "Student", "987654321", librarySystem);
        Member member2 = new Member("Bob", 2, "Faculty", "555123456", librarySystem);
        Member member3 = new Member("Charlie", 3, "Student", "111222333", librarySystem);

        librarySystem.addMember(member1);
        librarySystem.addMember(member2);
        librarySystem.addMember(member3);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Library Management System!");
        System.out.println("Please identify yourself:");
        System.out.println("1. Librarian");
        System.out.println("2. Member");
        System.out.println("3. Author");
        System.out.print("Enter your choice: ");

        int identityChoice = scanner.nextInt();
        scanner.nextLine();

        switch (identityChoice) {
            case 1:
                boolean isAuthenticated = false;
                int maxAttempts = 3;
                int attempts = 0;

                do {
                    System.out.print("Please enter your password: ");
                    String enteredPassword = scanner.nextLine();

                    if (librarian.authenticate(enteredPassword)) {
                        isAuthenticated = true;
                    } else {
                        attempts++;
                        System.out.println("Incorrect password. (Attempts left: " + (maxAttempts - attempts) + " )");
                    }
                } while (!isAuthenticated && attempts < maxAttempts);

                if (isAuthenticated) {
                    handleLibrarianOptions(librarySystem, librarian, scanner);
                } else {
                    System.out.println("Too many incorrect attempts. Exiting the program.");
                    System.exit(0);
                }
                break;

            case 2:
                Member member;
                boolean memberFound = false;
                do {
                    System.out.print("Enter Your Name: ");
                    String memberName = scanner.nextLine();

                    member = findMemberByName(librarySystem, memberName);

                    if (member != null) {
                        System.out.println("Welcome " + member.getName() + "!");
                        memberFound = true;
                    } else {
                        System.out.println("No member found with the name: " + memberName);
                    }
                } while (!memberFound);
                handleMemberOptions(librarySystem, member, scanner);
                break;



            case 3:
                System.out.print("Enter Author ID: ");
                int authorId = scanner.nextInt();
                scanner.nextLine();

                Author existingAuthor = librarySystem.getAuthor(authorId);

                if (existingAuthor != null) {
                    handleAuthorOptions(librarySystem, existingAuthor, scanner);
                } else {
                    System.out.println("Author not found with ID: " + authorId);
                }
                break;


            default:
                System.out.println("Invalid choice. Exiting the program.");
                System.exit(0);
        }
    }

    private static void handleLibrarianOptions(LibrarySystem librarySystem, Librarian librarian, Scanner scanner) {

        while (true) {
            System.out.println("Librarian Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Search Book");
            System.out.println("4. Add Member");
            System.out.println("5. Remove Member");
            System.out.println("6. Verify Member");
            System.out.println("7. Lend Book");
            System.out.println("8. Take Back Book");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int bookIdToAdd = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine();

                    System.out.print("Enter Author ID: ");
                    int authorId = scanner.nextInt();
                    scanner.nextLine();

                    Author author = librarySystem.getAuthor(authorId);
                    if (author == null) {
                        System.out.println("Author not found with ID: " + authorId);
                        break;
                    }

                    System.out.print("Enter Book Price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Enter Book Category(Science_Fiction, History, Education): ");
                    String categoryString = scanner.nextLine();
                    Category category = null;
                    while (category == null) {
                        try {
                            category = Category.valueOf(categoryString.toUpperCase());
                        } catch (Exception e) {
                            System.out.println("Invalid category. Please enter a valid category.");
                            System.out.print("Enter Book Category(Science_Fiction, History, Education): ");
                            categoryString = scanner.nextLine();
                        }
                    }
                    librarian.addBook(new Book(bookIdToAdd, title, author, price, category, false));
                    break;
                case 2:
                    System.out.print("Enter Book ID to Remove: ");
                    int bookIdToRemove = scanner.nextInt();
                    librarian.removeBook(bookIdToRemove);
                    break;
                case 3:
                    System.out.print("Enter Keyword to Search: ");
                    String keyword = scanner.nextLine();
                    librarian.searchBook(keyword);
                    break;
                case 4:
                    System.out.print("Enter Member ID: ");
                    int memberIdToAdd = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Member Name: ");
                    String memberName = scanner.nextLine();
                    librarian.addMember(new Member(memberName, memberIdToAdd, "Student", "123456789", librarySystem));
                    break;
                case 5:
                    System.out.print("Enter Member ID to Remove: ");
                    int memberIdToRemove = scanner.nextInt();
                    librarian.removeMember(memberIdToRemove);
                    break;
                case 6:
                    System.out.print("Enter Member ID to Verify: ");
                    int memberIdToVerify = scanner.nextInt();
                    librarian.verifyMember(memberIdToVerify);
                    break;
                case 7:
                    System.out.print("Enter Member ID: ");
                    int memberIdForBorrow = scanner.nextInt();
                    System.out.print("Enter Book ID: ");
                    int bookIdForBorrow = scanner.nextInt();
                    librarian.lendBook(memberIdForBorrow, bookIdForBorrow);
                    break;

                case 8:
                    System.out.print("Enter Member ID: ");
                    int memberIdToReturn = scanner.nextInt();

                    System.out.print("Enter Book ID: ");
                    int bookIdToReturn = scanner.nextInt();

                    librarian.takeBackBook(memberIdToReturn, bookIdToReturn);
                    break;

                case 0:
                    System.out.println("Exiting the program.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }
    private static void handleMemberOptions(LibrarySystem librarySystem, Member member, Scanner scanner) {

        while (true) {
            System.out.println("Member Menu:");
            System.out.println("1. Search Book");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int mainChoice = scanner.nextInt();
            scanner.nextLine();

            switch (mainChoice) {
                case 1:
                    handleSearchBookOptions(member, scanner);
                    break;
                case 2:
                    handleBorrowBookOptions(member, scanner);
                    break;
                case 3:
                    handleReturnBookOptions(member, scanner);
                    break;
                case 0:
                    System.out.println("Exiting the program.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }
    private static void handleSearchBookOptions(Member member, Scanner scanner) {
        System.out.println("Search Book Options:");
        System.out.println("1. Search Book by ID");
        System.out.println("2. Search Books by Title");
        System.out.println("3. Search Books by Author");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");

        int searchChoice = scanner.nextInt();
        scanner.nextLine();

        switch (searchChoice) {
            case 1:
                System.out.print("Enter Book ID to Search: ");
                int bookIdToSearch = scanner.nextInt();
                scanner.nextLine();
                Book searchedBookById = member.searchBookById(bookIdToSearch);
                if (searchedBookById != null) {
                    System.out.println("Found book: " + searchedBookById);
                }
                break;
            case 2:
                System.out.print("Enter Book Title to Search: ");
                String titleToSearch = scanner.nextLine();
                List<Book> booksByTitle = member.searchBooksByTitle(titleToSearch);
                if (!booksByTitle.isEmpty()) {
                    System.out.println("Found books: " + booksByTitle);
                }
                break;
            case 3:
                System.out.print("Enter Author Name to Search: ");
                String authorToSearch = scanner.nextLine();
                List<Book> booksByAuthor = member.searchBooksByAuthor(authorToSearch);
                if (!booksByAuthor.isEmpty()) {
                    System.out.println("Found books: " + booksByAuthor);
                }
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
                break;
        }
    }
    private static void handleBorrowBookOptions(Member member, Scanner scanner) {
        System.out.print("Enter Member ID: ");
        int memberId = scanner.nextInt();
        System.out.print("Enter Book ID to Borrow: ");
        int bookIdToBorrow = scanner.nextInt();
        member.borrowBook(memberId, bookIdToBorrow);
    }
    private static void handleReturnBookOptions(Member member, Scanner scanner) {
        System.out.print("Enter Member ID: ");
        int memberId = scanner.nextInt();
        System.out.print("Enter Book ID to Return: ");
        int bookIdToReturn = scanner.nextInt();
        member.returnBook(memberId, bookIdToReturn);
    }
    private static void handleAuthorOptions(LibrarySystem librarySystem, Author author, Scanner scanner) {
        while (true) {
            System.out.println("Author Menu:");
            System.out.println("1. View Written Books");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    System.out.println("Exiting the program.");
                    System.exit(0);
                    break;
                case 1:
                    viewWrittenBooks(author);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }
    private static void viewWrittenBooks(Author author) {
        System.out.println("Books written by " + author.getName() + ":");
        Map<Integer, Book> writtenBooks = author.getWrittenBooks();

        if (writtenBooks.isEmpty()) {
            System.out.println("No books written by this author.");
        } else {
            for (Book book : writtenBooks.values()) {
                System.out.println(" - " + book.getTitle());
            }
        }
    }


}
