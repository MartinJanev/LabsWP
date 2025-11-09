package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book> books = null;
    public static List<BookReservation> reservations = null;
    public static List<Author> authors = null;

    @PostConstruct
    public void init() {
        books = new ArrayList<>();
        reservations = new ArrayList<>();
        authors = new ArrayList<>();

        authors.add(new Author("Daniel", "Kahneman", "Israel", "Psychologist, Nobel laureate in Economics."));
        authors.add(new Author("Sun", "Tzu", "China", "Ancient Chinese military strategist and philosopher."));
        authors.add(new Author("Yuval Noah", "Harari", "Israel", "Historian and author of several bestsellers."));
        authors.add(new Author("James", "Clear", "USA", "Author focused on habits and behavior change."));
        authors.add(new Author("Andrew", "Hunt", "USA", "Software engineer and co-author of The Pragmatic Programmer."));
        authors.add(new Author("Robert C.", "Martin", "USA", "Software engineer and author, also known as 'Uncle Bob'."));
        authors.add(new Author("Mark", "Manson", "USA", "Self-help author and blogger."));
        authors.add(new Author("Marcus", "Aurelius", "Rome", "Roman emperor and Stoic philosopher."));
        authors.add(new Author("Viktor", "Frankl", "Austria", "Psychiatrist and Holocaust survivor."));
        authors.add(new Author("Cal", "Newport", "USA", "Computer science professor and productivity writer."));

        books.add(new Book("Thinking, Fast and Slow", "Psychology", 9.0, authors.get(0)));
        books.add(new Book("The Art of War", "Strategy", 8.8, authors.get(1)));
        books.add(new Book("Sapiens: A Brief History of Humankind", "History", 9.2, authors.get(2)));
        books.add(new Book("Atomic Habits", "Self-Help", 9.1, authors.get(3)));
        books.add(new Book("The Pragmatic Programmer", "Computer Science", 9.5, authors.get(4)));
        books.add(new Book("Clean Code", "Software Engineering", 9.4, authors.get(5)));
        books.add(new Book("The Subtle Art of Not Giving a F*ck", "Self-Help", 8.3, authors.get(6)));
        books.add(new Book("Meditations", "Philosophy", 8.9, authors.get(7)));
        books.add(new Book("Man’s Search for Meaning", "Psychology", 9.3, authors.get(8)));
        books.add(new Book("Deep Work", "Productivity", 8.7, authors.get(9)));
    }

}
