package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookReservationController {

    private final BookReservationService bookReservationService;

    public BookReservationController(BookReservationService bookReservationService) {
        this.bookReservationService = bookReservationService;
    }

    @PostMapping("/bookReservation")
    public String makeReservation(@RequestParam String bookTitle,
                                  @RequestParam String readerName,
                                  @RequestParam String readerAddress,
                                  @RequestParam Integer numCopies,
                                  HttpServletRequest request,
                                  Model model) {

        String clientIp = request.getRemoteAddr();

        BookReservation reservation =
                bookReservationService.placeReservation(bookTitle, readerName, readerAddress, numCopies);

        model.addAttribute("reservation", reservation);
        model.addAttribute("clientIp", clientIp);

        return "reservationConfirmation";
    }
}
