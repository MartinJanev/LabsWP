package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> listAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return this.bookRepository.searchBooks(text, rating);
    }

    @Override
    public Book findById(Long id) {
        return this.bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book create(String title, String genre, Double avgRating, Long authorId) {
        Author author = this.authorRepository.findById(authorId);

        if (author == null) throw new IllegalArgumentException("Author Not Found");

        Book book = new Book(title, genre, avgRating, author);
        return this.bookRepository.save(book);
    }

    @Override
    public Book update(Long id, String title, String genre, Double avgRating, Long authorId) {
        Book book = this.findById(id);
        if (book == null) throw new IllegalArgumentException("Book Not Found");

        Author author = this.authorRepository.findById(authorId);
        if (author == null) throw new IllegalArgumentException("Author Not Found");

        book.setTitle(title);
        book.setGenre(genre);
        book.setAverageRating(avgRating);
        book.setAuthor(author);

        return this.bookRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }
}
