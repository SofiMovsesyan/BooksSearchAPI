package tumo.bookapi.implementation.controllers.io;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tumo.bookapi.api.domain.Books;
import tumo.bookapi.api.services.BooksService;

import java.util.List;

@RestController
@RequestMapping("api/")
public class BooksController {

    private final BooksService booksService;

    public BooksController(BooksService booksService){
        this.booksService = booksService;
    }

    //Retrieves
    @GetMapping("{id}")
    public Books findByID(@PathVariable Long id ){
        Books books = this.booksService.findById(id);
        return books;
    }

    @GetMapping("{name}")
    public Books findByName(@PathVariable String name ){
        Books books = this.booksService.findByName(name);
        return books;
    }

    @GetMapping("{author}")
    public Books findByAuthor(@PathVariable String author ){
        Books books = this.booksService.findByAuthor(author);
        return books;
    }

    @GetMapping("{genre}")
    public Books findByGenre(@PathVariable String genre ){
        Books books = this.booksService.findByGenre(genre);
        return books;
    }

    @GetMapping("")
    public List<Books> findAll() {
        List<Books> allBooks = this.booksService.findAll();
        return allBooks;
    }










}
