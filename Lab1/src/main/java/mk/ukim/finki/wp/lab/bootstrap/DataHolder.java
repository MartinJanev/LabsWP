package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book> books = null;
    public static List<BookReservation> reservations = null;

    @PostConstruct
    public void init() {
        books = new ArrayList<>();
        reservations = new ArrayList<>();

        books.add(new Book("Thinking, Fast and Slow", "Psychology", 9.0));
        books.add(new Book("The Art of War", "Strategy", 8.8));
        books.add(new Book("Sapiens: A Brief History of Humankind", "History", 9.2));
        books.add(new Book("Atomic Habits", "Self-Help", 9.1));
        books.add(new Book("The Pragmatic Programmer", "Computer Science", 9.5));
        books.add(new Book("Clean Code", "Software Engineering", 9.4));
        books.add(new Book("The Subtle Art of Not Giving a F*ck", "Self-Help", 8.3));
        books.add(new Book("Meditations", "Philosophy", 8.9));
        books.add(new Book("Man’s Search for Meaning", "Psychology", 9.3));
        books.add(new Book("Deep Work", "Productivity", 8.7));


    }
}
