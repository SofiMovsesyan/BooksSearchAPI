package tumo.bookapi.api.services;

import jakarta.annotation.Nullable;
import tumo.bookapi.api.domain.Books;

import java.util.List;

public interface BooksService {
    Books findById(Long id);
    Books findByName(String name);
    Books findByAuthor(String name);
    Books findByGenre(String name);

    List<Books> findAll();

    Books saveProduct(
            String name,
            String author,
            String description,
            String genre);

    Books updateProduct(
            String name,
            String author,
            @Nullable String genre,
            @Nullable String description);

    void deleteProduct(Long id);
}
