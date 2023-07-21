package tumo.bookapi.api.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "book")
@Data
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "description")
    private String description;

    @Column(name = "genre")
    private String genre;

    public Books(String name, String author, String description, String genre) {
        this.name = name;
        this.author = author;
        this.description = description;
        this.genre = genre;
    }

    public Books() {

    }
}
