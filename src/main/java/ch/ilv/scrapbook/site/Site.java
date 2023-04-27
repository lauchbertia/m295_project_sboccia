package ch.ilv.scrapbook.site;

import ch.ilv.scrapbook.scrapbook.Scrapbook;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Site {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @Size(max = 25)
    @NotEmpty
    private String title;

    @Column
    @NotEmpty
    @Size(max = 2500)
    private String content;

    @Column
    private String comment;

    @ManyToOne
    @JoinColumn(name = "scrapbook_id")
    private Scrapbook scrapbook;

    public Site() {
    }
}
