package study.level.low;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum Genre {
    FICTION,
    NON_FICTION,
    MYSTERY,
    SCIENCE_FICTION
}

class Book<T extends Enum<T>> {
    private String title;
    private Genre genre;

    public Book(String title, Genre genre) {
        this.title = title;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public Genre getGenre() {
        return genre;
    }
}

class Library<T extends Enum<T>> {
    private Map<Genre, List<Book<?>>> bookCatalog;

    public Library() {
        this.bookCatalog = new HashMap<>();
        for (Genre genre : Genre.values()) {
            bookCatalog.put(genre, new ArrayList<>());
        }
    }

    public void addBook(Book<?> book) {
        bookCatalog.get(book.getGenre()).add(book);
    }

    public void printBooks(Genre genre) {
        System.out.println("Books in " + genre + " genre:");

        for (Book<?> book : bookCatalog.get(genre)) {
            System.out.println("  - Title: " + book.getTitle());
        }
    }

    public void printAllBooks() {
        System.out.println("All Books:");

        for (Map.Entry<Genre, List<Book<?>>> entry : bookCatalog.entrySet()) {
            Genre genre = entry.getKey();
            List<Book<?>> books = entry.getValue();

            System.out.println("Genre: " + genre);
            for (Book<?> book : books) {
                System.out.println("  - Title: " + book.getTitle());
            }
        }
    }
}

public class LibraryExample {
    public static void main(String[] args) {
        Book<Genre> fictionBook = new Book<>("To Kill a Mockingbird", Genre.FICTION);
        Book<Genre> nonFictionBook = new Book<>("Sapiens", Genre.NON_FICTION);
        Book<Genre> mysteryBook = new Book<>("The Girl with the Dragon Tattoo", Genre.MYSTERY);

        Library<Genre> myLibrary = new Library<>();
        myLibrary.addBook(fictionBook);
        myLibrary.addBook(nonFictionBook);
        myLibrary.addBook(mysteryBook);

        myLibrary.printBooks(Genre.FICTION);
        myLibrary.printAllBooks();
    }
}