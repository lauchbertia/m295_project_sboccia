package ch.ilv.scrapbook.scrapbook;

import ch.ilv.scrapbook.base.MessageResponse;
import ch.ilv.scrapbook.dataaccess.EntityNotFoundException;
import ch.ilv.scrapbook.rating.Rating;
import ch.ilv.scrapbook.rating.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScrapbookService {

    private final ScrapbookRepository repository;
    public ScrapbookService(ScrapbookRepository repository) {
        this.repository = repository;
    }
    public List<Scrapbook> getScrapbooks() {
        return repository.findOrderByNameAsc();
    }
    public Scrapbook getScrapbook(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Scrapbook.class));
    }
    public Scrapbook updateScrapbook(Scrapbook scrapbook, Long id) {
        return repository.save(scrapbook);
    }

    public Scrapbook insertScrapbook(Scrapbook scrapbook) {
        return repository.save(scrapbook);
    }

    public MessageResponse deleteScrapbook(Long id) {
        repository.deleteById(id);
        return new MessageResponse("Scrapbook" + id + " deleted");
    }
}
