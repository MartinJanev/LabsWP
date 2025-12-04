package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Book;

import java.util.List;

public interface BookService {
    List<Book> listAll();

    List<Book> searchBooks(String text, Double rating);

    Book findById(Long id);

    Book create(String title, String genre, Double avgRating, Long authorId);

    Book update(Long id, String title, String genre, Double avgRating, Long authorId);

    void deleteById(Long id);

    List<Book> listByAuthor(Long authorId);
}
