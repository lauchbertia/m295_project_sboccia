package ch.ilv.scrapbook.scrapbook;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Entity
public class Scrapbook {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @Size(max = 255)
    @NotEmpty
    private String title;

    @Column()
    private Integer rating;

    public Scrapbook() {
    }

    public Scrapbook(String title, int rating) {
        this.title = title;
        this.rating = rating;
    }


}
