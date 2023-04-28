package ch.ilv.scrapbook.scrapbook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScrapbookRepository extends JpaRepository<Scrapbook, Long> {
    //List<Scrapbook> findOrderByNameAsc();



}
