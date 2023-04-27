package ch.ilv.scrapbook.comment;

import ch.ilv.scrapbook.scrapbook.Scrapbook;
import ch.ilv.scrapbook.site.Site;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @NotEmpty
    private String content;

    @OneToOne
    @JoinColumn(name = "site_id")
    private Site site;

    public Comment() {
    }
}
