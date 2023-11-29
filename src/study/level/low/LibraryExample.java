package study.level.low;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum Genre {
    FICTION, // 창작 소설
    NON_FICTION, // 실화 소설
    MYSTERY, // 미스터리
    SCIENCE_FICTION // 공상 과학 소설
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
        System.out.println(genre + " 장르:");

        for (Book<?> book : bookCatalog.get(genre)) {
            System.out.println("  - 제목: " + book.getTitle());
        }
    }

    public void printAllBooks() {
        System.out.println("모든 책:");

        for (Map.Entry<Genre, List<Book<?>>> entry : bookCatalog.entrySet()) {
            Genre genre = entry.getKey();
            List<Book<?>> books = entry.getValue();

            System.out.println("장르: " + genre);
            for (Book<?> book : books) {
                System.out.println("  - 제목: " + book.getTitle());
            }
        }
    }
}

public class LibraryExample {
    public static void main(String[] args) {
        Book<Genre> fictionBook = new Book<>("톰 소여의 모험", Genre.FICTION);
        Book<Genre> nonFictionBook = new Book<>("사피엔스", Genre.NON_FICTION);
        Book<Genre> mysteryBook = new Book<>("그리고 아무도 없었다", Genre.MYSTERY);

        Library<Genre> myLibrary = new Library<>();
        myLibrary.addBook(fictionBook);
        myLibrary.addBook(nonFictionBook);
        myLibrary.addBook(mysteryBook);

        myLibrary.printBooks(Genre.FICTION);
        myLibrary.printAllBooks();
    }
