package mk.ukim.finki.wp.lab.service.extra;

import mk.ukim.finki.wp.lab.model.BookReservation;

import java.util.List;

public interface BookReservationServiceExtra {
    BookReservation placeReservation(String bookTitle,
                                     String readerName,
                                     String readerAddress,
                                     int numberOfCopies);

    //Za dopolnitelnoto baranje
    List<BookReservation> getLastThreeReservations();

}
