package tumo.bookapi.implementation.services;

import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;
import tumo.bookapi.api.domain.Books;
import tumo.bookapi.api.repositories.BooksRepository;
import tumo.bookapi.api.services.BooksService;

import java.util.List;
import java.util.Optional;

@Service
public class BooksServiceImpl implements BooksService {
    private final BooksRepository booksRepository;

    public BooksServiceImpl(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Override
    public Books findById(Long id) {
        return null;
    }

    @Override
    public Books findByName(String name) {
        return null;
    }

    @Override
    public Books findByAuthor(String name) {
        return null;
    }

    @Override
    public Books findByGenre(String name) {
        return null;
    }

    @Override
    public List<Books> findAll() {
        return null;
    }

    @Override
    public Books saveBooks(String name, String author, String description, String genre) {
        return null;
    }

    @Override
    public Books updateBooks(String name, String author, @Nullable String genre, @Nullable String description) {
        return null;
    }

    @Override
    public void deleteBooks(Long id) {

    }
}
