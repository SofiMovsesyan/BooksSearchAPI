package tumo.bookapi.implementation.services;

import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;
import tumo.bookapi.api.domain.Book;
import tumo.bookapi.api.repositories.BookRepository;
import tumo.bookapi.api.services.BookService;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book findById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.get();
    }

    @Override
    public Book findByName(String name) {
        Optional<Book> book = bookRepository.findBookByName(name);
        return book.get();
    }

    @Override
    public Book findByAuthor(String author) {
        Optional<Book> book = bookRepository.findBookByAuthor(author);
        return book.get();
    }

    @Override
    public Book findByGenre(String genre) {
        Optional<Book> book = bookRepository.findBookByGenre(genre);
        return book.get();
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book saveBook(String name, String author, String genre, String description ) {
        Book newBook = new Book(name, author, genre, description);
        bookRepository.saveAndFlush(newBook);
        return newBook;
    }

    @Override
    public Book updateBook(String name, String author, @Nullable String genre, @Nullable String description) {
       Optional<Book> book = bookRepository.findBookByName(name);
       if (book.isPresent()){
           if (genre != null){
               book.get().setGenre(genre);
           }
           if(description !=null) {
               book.get().setDescription(description);
           }

           Book savedBook = bookRepository.save(book.get());
           return savedBook;
       }
       return null;
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);

    }
}
