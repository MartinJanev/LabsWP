package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorRepository {

    private final List<Author> authors = new ArrayList<>();

    public AuthorRepository() {
        // иницијализирај 3 автори
        authors.add(new Author("George", "Orwell", "UK", "Author of 1984 and Animal Farm."));
        authors.add(new Author("J.K.", "Rowling", "UK", "Author of the Harry Potter series."));
        authors.add(new Author("Fyodor", "Dostoevsky", "Russia", "Author of Crime and Punishment."));
    }

    public List<Author> findAll() {
        return authors;
    }

    public Author findById(Long id) {
        return authors.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
