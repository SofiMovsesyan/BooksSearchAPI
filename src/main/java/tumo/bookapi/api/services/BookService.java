package tumo.bookapi.api.services;

import com.google.api.services.books.v1.model.Volume;
import jakarta.annotation.Nullable;
import tumo.bookapi.api.domain.Book;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface BookService {
    Book findById(Long id);
    List<Book> findByName(String name) throws IOException, GeneralSecurityException;
    List<Book> findByAuthor(String name);
    List<Book> findByGenre(String name);

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

    List<Volume> searchBooks(String query);
}
