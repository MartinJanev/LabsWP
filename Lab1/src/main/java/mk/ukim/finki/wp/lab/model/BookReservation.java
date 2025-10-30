package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookReservation {
    String bookTitle, readerName, readerAddress;
    Long numberOfCopies;
}
