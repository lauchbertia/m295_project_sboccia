package ch.ilv.scrapbook.site;

import ch.ilv.scrapbook.base.MessageResponse;
import ch.ilv.scrapbook.dataaccess.EntityNotFoundException;
import ch.ilv.scrapbook.scrapbook.Scrapbook;
import ch.ilv.scrapbook.scrapbook.ScrapbookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SiteService {
    private final SiteRepository repository;
    private final ScrapbookRepository scrapbookRepository;

    public SiteService(SiteRepository repository, ScrapbookRepository scrapbookRepository) {
        this.repository = repository;
        this.scrapbookRepository = scrapbookRepository;
    }
    public List<Site> getSites() {
        return repository.findAll();
    }
    public List<Site> getSitesByScrapbook(Long scrapbook_id) {
        return repository.findByScrapbook(scrapbookRepository.findById(scrapbook_id).get());
    }

    public Site getSite(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Site.class));
    }

    public Site updateSite(Site site, Long id) {
        return repository.findById(id)
                .map(siteOrig -> {
                    siteOrig.setScrapbook(site.getScrapbook());
                    return repository.save(siteOrig);
                })
                .orElseGet(() -> repository.save(site));
    }

    public Site insertSite(Site site) {
        return repository.save(site);
    }


    public MessageResponse deleteSite(Long id) {
        repository.deleteById(id);
        return new MessageResponse("Site" + id + " deleted");
    }
}
