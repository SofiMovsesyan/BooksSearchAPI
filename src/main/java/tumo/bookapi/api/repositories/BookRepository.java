package tumo.bookapi.api.repositories;

import com.google.api.services.books.v1.model.Volume;
import org.springframework.data.jpa.repository.JpaRepository;
import tumo.bookapi.api.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findBookByName(String name);
    Optional<Book> findBookByAuthor(String name);
    Optional<Book> findBookByGenre(String name);

}
