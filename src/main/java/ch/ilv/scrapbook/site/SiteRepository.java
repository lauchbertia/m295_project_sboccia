package ch.ilv.scrapbook.site;

import ch.ilv.scrapbook.scrapbook.Scrapbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {
    List<Site> findByScrapbook(Scrapbook scrapbook);
    //   List<Site> findOrderByNameAsc();
}
