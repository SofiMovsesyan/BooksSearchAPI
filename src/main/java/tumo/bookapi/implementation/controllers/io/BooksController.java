package tumo.bookapi.implementation.controllers.io;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;
import tumo.bookapi.api.domain.Books;
import tumo.bookapi.api.services.BooksService;

import java.util.List;

@RestController
@RequestMapping("api/")
public class BooksController {

    private final BooksService booksService;


    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    //Retrieves
    @GetMapping("{id}")
    public Books findByID(@PathVariable Long id ){
        Books books = this.booksService.findById(id);
        return books;
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

    @PostMapping("")
    public Books createProduct(
            @RequestParam String name,
            @RequestParam String author,
            @RequestParam String genre,
            @RequestParam String description) {
        Books product = this.booksService.saveBooks(name, author, genre, description);
        return product;
    }

    //Update
    @PutMapping("")
    public Books updateProduct(
            @RequestParam String name,
            @RequestParam String author,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String description
    ) {
        Books product = this.booksService.updateBooks(name, author, genre, description);
        return product;
    }

    //Delete
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        this.booksService.deleteBooks(id);
    }



}
