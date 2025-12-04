package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookListServlet", urlPatterns = "")
public class BookListServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final BookService bookService;
    private final AuthorService authorService;

    public BookListServlet(SpringTemplateEngine templateEngine, BookService bookService, AuthorService authorService) {
        this.templateEngine = templateEngine;
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext webContext = new WebContext(webExchange);

        String text = req.getParameter("text");
        String ratingParam = req.getParameter("rating");

        Double ratingFilter = 0.0; // Default to 0 as fallback
        if (ratingParam != null && !ratingParam.isEmpty()) {
            try {
                ratingFilter = Double.parseDouble(ratingParam);
            } catch (NumberFormatException ignored) {
                ratingFilter = 0.0; // Fallback to 0 if parsing fails
            }
        }

        List<Book> books;

        if ((text != null && !text.isEmpty()) || (ratingParam != null && !ratingParam.isEmpty())) {
            books = bookService.searchBooks(text == null ? "" : text, ratingFilter);
        } else {
            books = bookService.listAll();
        }
        webContext.setVariable("books", books);

        webContext.setVariable("searchText", text == null ? "" : text);
        webContext.setVariable("searchRating", ratingParam == null ? 0 : ratingParam);
        webContext.setVariable("authors", authorService.findAll());
        webContext.setVariable("selectedAuthorId", null);

        resp.setContentType("text/html; charset=UTF-8");
        this.templateEngine.process("listBooks.html", webContext, resp.getWriter());
    }

}
