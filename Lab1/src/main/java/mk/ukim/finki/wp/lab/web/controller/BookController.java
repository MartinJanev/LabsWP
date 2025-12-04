package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }


    @GetMapping
    public String getBooksPage(
            @RequestParam(required = false) String error,
            @RequestParam(required = false) Long authorId,
            Model model
    ) {

        List<Book> books;
        if (authorId != null) {
            books = this.bookService.listByAuthor(authorId);
        } else {
            books = this.bookService.listAll();
        }
        model.addAttribute("books", books);
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("error", error);
        model.addAttribute("selectedAuthorId", authorId);

        return "listBooks";
    }

    @PostMapping("/add")
    public String saveBook(@RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId) {
        bookService.create(title, genre, averageRating, authorId);
        return "redirect:/books";
    }

    @PostMapping("/edit/{bookId}")
    public String editBook(@PathVariable Long bookId,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId) {
        bookService.update(bookId, title, genre, averageRating, authorId);
        return "redirect:/books";
    }

    @GetMapping("/delete/{bookId}")
    public String deleteBook(@PathVariable Long bookId) {
        bookService.deleteById(bookId);
        return "redirect:/books";
    }


    @GetMapping("/book-form/{bookId}")
    public String getEditBookForm(
            @PathVariable Long bookId,
            Model model
    ) {
        Book book = bookService.findById(bookId);
        if (book == null) return "redirect:/books?error=BookNotFound";
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.findAll());
        return "book-form";
    }

    @GetMapping("/book-form")
    public String getAddBookForm(Model model) {
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("book", null);
        return "book-form";
    }


}
