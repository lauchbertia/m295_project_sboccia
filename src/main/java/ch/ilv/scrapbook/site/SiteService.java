package ch.ilv.scrapbook.site;

import ch.ilv.scrapbook.base.MessageResponse;
import ch.ilv.scrapbook.dataaccess.EntityNotFoundException;
import ch.ilv.scrapbook.rating.Rating;
import ch.ilv.scrapbook.rating.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteService {
    private final SiteRepository repository;
    public SiteService(SiteRepository repository) {
        this.repository = repository;
    }
    public List<Site> getSites() {
        return repository.findOrderByNameAsc();
    }

    public Site getSite(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Site.class));
    }

    public Site updateSite(Site site, Long id) {
        return repository.save(site);
    }

    public Site insertSite(Site site) {
        return repository.save(site);
    }


    public MessageResponse deleteSite(Long id) {
        repository.deleteById(id);
        return new MessageResponse("Site" + id + " deleted");
    }
}
