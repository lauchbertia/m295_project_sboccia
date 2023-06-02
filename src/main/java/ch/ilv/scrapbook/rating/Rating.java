package ch.ilv.scrapbook.rating;

import ch.ilv.scrapbook.scrapbook.Scrapbook;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Rating {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private int stars;

    @OneToOne
    @JoinColumn(name = "scrapbook_id")
    private Scrapbook scrapbook;

    public Rating() {
    }
}
