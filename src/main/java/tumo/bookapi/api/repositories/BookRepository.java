package tumo.bookapi.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tumo.bookapi.api.domain.Book;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findBookByName(String name);
    Optional<Book> findBookByAuthor(String name);
    Optional<Book> findBookByGenre(String name);

}
