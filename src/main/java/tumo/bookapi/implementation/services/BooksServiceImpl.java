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
        Optional<Books> books = booksRepository.findById(id);
        return books.get();
    }

    @Override
    public Books findByName(String name) {
        Optional<Books> books = booksRepository.findBookByName(name);
        return books.get();
    }

    @Override
    public Books findByAuthor(String author) {
        Optional<Books> books = booksRepository.findBookByAuthor(author);
        return books.get();
    }

    @Override
    public Books findByGenre(String genre) {
        Optional<Books> books = booksRepository.findBookByGenre(genre);
        return books.get();
    }

    @Override
    public List<Books> findAll() {
        return booksRepository.findAll();
    }

    @Override
    public Books saveBooks(String name, String author, String genre, String description ) {
        Books newBooks = new Books(name, author, genre, description);
        booksRepository.saveAndFlush(newBooks);
        return newBooks;
    }

    @Override
    public Books updateBooks(String name, String author, @Nullable String genre, @Nullable String description) {
       Optional<Books> books = booksRepository.findBookByName(name);
       if (books.isPresent()){
           if (genre != null){
               books.get().setGenre(genre);
           }
           if(description !=null) {
               books.get().setDescription(description);
           }

           Books savedBooks = booksRepository.save(books.get());
           return savedBooks;
       }
       return null;
    }

    @Override
    public void deleteBooks(Long id) {
        booksRepository.deleteById(id);

    }
}
