package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryBookReservationRepository implements BookReservationRepository {


    @Override
    public BookReservation save(BookReservation bookReservation) {
        DataHolder.reservations.removeIf(r -> r.getBookTitle().equals(bookReservation.getBookTitle()));
        DataHolder.reservations.add(bookReservation);
        return bookReservation;
    }

}
