package ch.ilv.scrapbook.scrapbook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapbookRepository extends JpaRepository<Scrapbook, Long> {
}
