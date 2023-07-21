package tumo.bookapi.api.services;

import jakarta.annotation.Nullable;
import tumo.bookapi.api.domain.Book;

import java.util.List;

public interface BookService {
    Book findById(Long id);
    Book findByName(String name);
    Book findByAuthor(String name);
    Book findByGenre(String name);

    List<Book> findAll();

    Book saveBook(
            String name,
            String author,
            String description,
            String genre);

    Book updateBook(
            String name,
            String author,
            @Nullable String genre,
            @Nullable String description);

    void deleteBook(Long id);
}
