package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.repository.jpa.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.jpa.BookRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book> books = null;
    public static List<BookReservation> reservations = null;
    public static List<Author> authors = null;

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;


    public DataHolder(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @PostConstruct
    public void init() {
        books = new ArrayList<>();
        reservations = new ArrayList<>();
        authors = new ArrayList<>();

        if (authorRepository.findAll().isEmpty()) {

            // Fewer authors, more books per author
            authors.add(authorRepository.save(
                    new Author("Daniel", "Kahneman", "Israel", "Psychologist and Nobel laureate.")));  // index 0

            authors.add(authorRepository.save(
                    new Author("Yuval Noah", "Harari", "Israel", "Historian and bestselling author."))); // index 1

            authors.add(authorRepository.save(
                    new Author("James", "Clear", "USA", "Author focused on habits and behavior change."))); // index 2

            authors.add(authorRepository.save(
                    new Author("Robert C.", "Martin", "USA", "Software engineer and author of clean code principles."))); // index 3

            authors.add(authorRepository.save(
                    new Author("Cal", "Newport", "USA", "Computer science professor and productivity writer."))); // index 4
        }

        if (bookRepository.findAll().isEmpty()) {

            // Daniel Kahneman
            books.add(bookRepository.save(new Book("Thinking, Fast and Slow", "Psychology", 9.0, authors.get(0))));
            books.add(bookRepository.save(new Book("Noise: A Flaw in Human Judgment", "Psychology", 8.6, authors.get(0))));

            // Yuval Noah Harari
            books.add(bookRepository.save(new Book("Sapiens", "History", 9.2, authors.get(1))));
            books.add(bookRepository.save(new Book("Homo Deus", "History", 9.0, authors.get(1))));
            books.add(bookRepository.save(new Book("21 Lessons for the 21st Century", "History", 8.8, authors.get(1))));

            // James Clear
            books.add(bookRepository.save(new Book("Atomic Habits", "Self-Help", 9.1, authors.get(2))));
            books.add(bookRepository.save(new Book("Transform Your Habits", "Self-Help", 8.4, authors.get(2))));

            // Robert C. Martin
            books.add(bookRepository.save(new Book("Clean Code", "Software Engineering", 9.4, authors.get(3))));
            books.add(bookRepository.save(new Book("Clean Architecture", "Software Engineering", 9.2, authors.get(3))));
            books.add(bookRepository.save(new Book("The Clean Coder", "Software Engineering", 9.0, authors.get(3))));

            // Cal Newport
            books.add(bookRepository.save(new Book("Deep Work", "Productivity", 8.7, authors.get(4))));
            books.add(bookRepository.save(new Book("Digital Minimalism", "Productivity", 8.9, authors.get(4))));
            books.add(bookRepository.save(new Book("So Good They Can't Ignore You", "Career", 8.8, authors.get(4))));
        }
    }


}
