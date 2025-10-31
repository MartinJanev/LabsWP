package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "BookReservationServlet", urlPatterns = "/bookReservation")
public class BookReservationServlet extends HttpServlet {
    private final TemplateEngine templateEngine;
    private final BookReservationService bookReservationService;

    public BookReservationServlet(TemplateEngine templateEngine,
                                  BookReservationService bookReservationService) {
        this.templateEngine = templateEngine;
        this.bookReservationService = bookReservationService;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookTitle = req.getParameter("bookTitle");
        String readerName = req.getParameter("readerName");
        String readerAddress = req.getParameter("readerAddress");
        String numCopiesStr = req.getParameter("numCopies");

        int numCopies = 1;
        if (numCopiesStr != null && !numCopiesStr.isEmpty()) {
            try {
                numCopies = Integer.parseInt(numCopiesStr);
            } catch (NumberFormatException ignored) {
            }
        }

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext webContext = new WebContext(webExchange);


        BookReservation reservation = bookReservationService.placeReservation(
                bookTitle, readerName, readerAddress, numCopies
        );

        HashMap<String, Integer> booksMap = (HashMap<String, Integer>) getServletContext().getAttribute("hashMapBooks");

        if (!booksMap.containsKey(bookTitle)) {
            booksMap.put(bookTitle, 1);
        } else {
            booksMap.put(bookTitle, booksMap.get(bookTitle) + 1);
        }

        getServletContext().setAttribute("hashMapBooks", booksMap);
        webContext.setVariable("hashMapBooks", booksMap);

        webContext.setVariable("reservation", reservation);
        webContext.setVariable("clientIp", req.getRemoteAddr());

        resp.setContentType("text/html; charset=UTF-8");
        templateEngine.process("reservationConfirmation.html", webContext, resp.getWriter());
    }
}