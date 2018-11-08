package controller;

import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.BookService;

@Controller
@RequestMapping("/")  // обрабатывает Mapping начинающий со "/"
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")// обработка GET-запроса по адресу /
    public String index() {
        return "index";
    }

    @GetMapping("/books")
    public String listBooks(Model model) {
        model.addAttribute("book", new Book()); // "book" = new Book (для работы в html)
        model.addAttribute("listBooks", bookService.listBooks()); //listBooks = bookService.listBooks(для работы в html)
        return "books";
    }

    @PostMapping("/books/add")
    public String addBook (@ModelAttribute ("book") Book book) {
        if (book.getId() == 0) {
            bookService.addBook(book);
        } else {
            bookService.updateBook(book);
        }
        return "redirect:/books"; // переадресация
    }
    @GetMapping("/remove/{id}")
    public String removeBook(@PathVariable("id") int id) {
        bookService.removeBook(id);
        return "redirect:/books"; // переадресация
    }
    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        model.addAttribute("listBooks", bookService.listBooks());
        return "books";
    }
    @GetMapping("/bookdata/{id}")
    public String bookdata(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        return "bookdata";
    }

}
