package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.jpa.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.jpa.BookRepository;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.data.domain.Sort;
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
        return this.bookRepository.findAll(Sort.by("author.name")
        );
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {

        boolean hasText = (text != null && !text.isBlank());
        boolean hasRating = (rating != null);

        if (hasText && hasRating) {
            // title contains text, rating > argument, sorted by author name
            return this.bookRepository
                    .findAllByTitleContainingIgnoreCaseAndAverageRatingGreaterThanOrderByAuthor_Name(text, rating);
        } else if (hasText) {
            // only title filter, sorted by author name
            return this.bookRepository
                    .findAllByTitleContainingIgnoreCaseOrderByAuthor_Name(text);
        } else if (hasRating) {
            // only rating filter, sorted by author name
            return this.bookRepository
                    .findAllByAverageRatingGreaterThanOrderByAuthor_Name(rating);
        } else {
            // no filters → just sort by author name
            return this.bookRepository.findAll(Sort.by("author.name"));
        }
    }


    @Override
    public Book findById(Long id) {
        return this.bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book create(String title, String genre, Double avgRating, Long authorId) {
        Author author = this.authorRepository.findById(authorId).orElse(null);

        if (author == null) throw new IllegalArgumentException("Author Not Found");

        Book book = new Book(title, genre, avgRating, author);
        return this.bookRepository.save(book);
    }

    @Override
    public Book update(Long id, String title, String genre, Double avgRating, Long authorId) {
        Book book = this.findById(id);
        if (book == null) throw new IllegalArgumentException("Book Not Found");

        Author author = this.authorRepository.findById(authorId).orElse(null);
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

    @Override
    public List<Book> listByAuthor(Long authorId) {
        return this.bookRepository.findAllByAuthor_Id(authorId);
    }
}
