package mk.ukim.finki.wp.lab.service.extra;

import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.repository.BookReservationRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class BookReservationServiceImplExtra implements BookReservationServiceExtra {

    private final BookReservationRepository bookReservationRepository;
    private final LinkedList<BookReservation> lastThreeReservations = new LinkedList<>();

    public BookReservationServiceImplExtra(BookReservationRepository bookReservationRepository) {
        this.bookReservationRepository = bookReservationRepository;
    }

    @Override
    public BookReservation placeReservation(String bookTitle, String readerName, String readerAddress, int numberOfCopies) {
        if (bookTitle == null || bookTitle.isEmpty()
                || readerName == null || readerName.isEmpty()
                || readerAddress == null || readerAddress.isEmpty()
                || numberOfCopies < 0) {
            throw new IllegalArgumentException();
        }

        BookReservation reservation = new BookReservation(bookTitle, readerName, readerAddress, (long) numberOfCopies);

        bookReservationRepository.save(reservation);

        if (lastThreeReservations.size() == 3) {
            lastThreeReservations.removeFirst();
        }
        lastThreeReservations.addLast(reservation);

        return reservation;
    }

    public List<BookReservation> getLastThreeReservations() {
        return List.copyOf(lastThreeReservations);
    }
}
