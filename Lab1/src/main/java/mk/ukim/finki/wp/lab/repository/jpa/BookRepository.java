package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByAuthor_Id(Long authorId);

    // OLD – you can keep it if you still need it
    // List<Book> findAllByTitleAndAverageRating(String title, double rating);

    // NEW: rating filter only, sorted by author name
    List<Book> findAllByAverageRatingGreaterThanOrderByAuthor_Name(Double rating);

    // NEW: title search only, sorted by author name
    List<Book> findAllByTitleContainingIgnoreCaseOrderByAuthor_Name(String title);

    // NEW: title search + rating filter, sorted by author name
    List<Book> findAllByTitleContainingIgnoreCaseAndAverageRatingGreaterThanOrderByAuthor_Name(
            String title,
            Double rating
    );
}
