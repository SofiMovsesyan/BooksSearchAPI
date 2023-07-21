package tumo.bookapi.implementation.controllers.io;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;
import tumo.bookapi.api.domain.Book;
import tumo.bookapi.api.services.BookService;

import java.util.List;

@RestController
@RequestMapping("api/")
public class BookController {

    private final BookService bookService;


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //Retrieves
    @GetMapping("{id}")
    public Book findByID(@PathVariable Long id ){
        Book book = this.bookService.findById(id);
        return book;
    }



    @Operation(
            summary = "Retrieves a specific book by its name",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieves the book"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error"
                    ),
            }
    )

    @GetMapping("{name}")
    public Book findByName(@PathVariable String name ){
        Book book = this.bookService.findByName(name);
        return book;
    }

    @GetMapping("{author}")
    public Book findByAuthor(@PathVariable String author ){
        Book book = this.bookService.findByAuthor(author);
        return book;
    }

    @GetMapping("{genre}")
    public Book findByGenre(@PathVariable String genre ){
        Book book = this.bookService.findByGenre(genre);
        return book;
    }

    @GetMapping("")
    public List<Book> findAll() {
        List<Book> allBooks = this.bookService.findAll();
        return allBooks;
    }

    @PostMapping("")
    public Book createBook(
            @RequestParam String name,
            @RequestParam String author,
            @RequestParam String genre,
            @RequestParam String description) {
        Book book = this.bookService.saveBook(name, author, genre, description);
        return book;
    }

    //Update
    @PutMapping("")
    public Book updateBook(
            @RequestParam String name,
            @RequestParam String author,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String description
    ) {
        Book book = this.bookService.updateBook(name, author, genre, description);
        return book;
    }

    //Delete
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        this.bookService.deleteBook(id);
    }



}