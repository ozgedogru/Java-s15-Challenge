package library;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Author extends Person {
    private int authorId;
    private Map<Integer, Book> writtenBooks;

    public Author(Identity whoYouAre, String name, int authorId,  Map<Integer, Book> writtenBooks) {
        super(whoYouAre, name);
        this.authorId = authorId;
        this.writtenBooks = new HashMap<>();
    }

    public int getAuthorId() {
        return authorId;
    }
    public Map<Integer, Book> getWrittenBooks() {
        return writtenBooks;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Author author = (Author) obj;
        return authorId == author.authorId &&
                Objects.equals(getName(), author.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, getName());
    }

    @Override
    public String toString() {
        return "Author: " + getName() + "\n" +
                "Books: " + getWrittenBooks();
    }
}
