package ch.ilv.scrapbook.scrapbook;

import ch.ilv.scrapbook.base.MessageResponse;
import ch.ilv.scrapbook.dataaccess.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScrapbookService {

    private final ScrapbookRepository repository;
    public ScrapbookService(ScrapbookRepository repository) {
        this.repository = repository;
    }
    public List<Scrapbook> getScrapbooks() {
        return repository.findAll();
    }
    public Scrapbook getScrapbook(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Scrapbook.class));
    }
    public Scrapbook updateScrapbook(Scrapbook scrapbook, Long id) {

        return repository.findById(id)
                .map(scrapbookOrig -> {
                    scrapbookOrig.setTitle(scrapbook.getTitle());
                    return repository.save(scrapbookOrig);
                })
                .orElseGet(() -> repository.save(scrapbook));
    }

    public Scrapbook insertScrapbook(Scrapbook scrapbook) {
        return repository.save(scrapbook);
    }

    public MessageResponse deleteScrapbook(Long id) {
        repository.deleteById(id);
        return new MessageResponse("Scrapbook" + id + " deleted");
    }
}
