package tumo.bookapi.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tumo.bookapi.api.domain.Books;

import java.util.Optional;

public interface BooksRepository extends JpaRepository<Books, Long> {
    Optional<Books> findProductByName(String name);
    Optional<Books> findProductByAuthor(String name);
    Optional<Books> findProductByGenre(String name);

}
