package tumo.bookapi.implementation.services;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;
import tumo.bookapi.api.domain.Book;
import tumo.bookapi.api.repositories.BookRepository;
import tumo.bookapi.api.services.BookService;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.books.v1.Books;
import com.google.api.services.books.v1.BooksRequestInitializer;
import com.google.api.services.books.v1.model.Volume;
import com.google.api.services.books.v1.model.Volumes;

@Service
public abstract class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private static final String APPLICATION_NAME = "Books Search API";
    private static final String API_KEY = "AIzaSyDhWyRLaKQcVxuO__PCuH9k4JwSU531z0Y";
    private NetHttpTransport httpTransport;
    private JsonFactory jsonFactory;
    private Books books;
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
//        this.httpTransport = httpTransport;
//        this.jsonFactory = jsonFactory;
    }

    @Override
    public Book findById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.get();
    }

    @Override
    public Book findByName(String name) throws IOException {
        Optional<Book> book = bookRepository.findBookByName(name);

        // Potential Future enhancement
//        // What if we dont have this book in our database?
//        // Then go fetch from google API
//        fetchBooksFromGoogleApi
        if (!book.isPresent()) {
            List<Volume> googleBook = findBookFromGoogleApi(name);
            if (googleBook != null) {
                // Save the book retrieved from Google API to your database
                /*bookRepository.saveAndFlush(googleBook);
                return googleBook;*/

                Book bookToSave = convertToBook(googleBook.get(0));
                bookRepository.save(bookToSave);
                return bookToSave;
            }
        }
        return book.get();
    }

    private Book convertToBook(Volume volume) {
        Book book = new Book();

        // Assuming Volume contains information like title, authors, description, genre, etc.
        book.setName(volume.getVolumeInfo().getTitle());
        book.setAuthor(volume.getVolumeInfo().getAuthors().get(0)); // Assuming a single author
        book.setDescription(volume.getVolumeInfo().getDescription());
        // Set other properties as needed
        book.setGenre("Unknown"); // You can set a default genre or handle it differently

        return book;
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



    public List<Volume> findBookFromGoogleApi(String name) throws IOException{
        Volumes volumes = books.volumes().list(name).execute();
        return volumes.getItems();
    }

    public void fetchBooksFromGoogleApi() throws GeneralSecurityException, IOException {


        books = new Books.Builder(GoogleNetHttpTransport.newTrustedTransport(), jsonFactory, null)
                .setApplicationName(APPLICATION_NAME)
                .setGoogleClientRequestInitializer(new BooksRequestInitializer(API_KEY))
                .build();
    }
}
