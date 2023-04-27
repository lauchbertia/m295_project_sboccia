package ch.ilv.scrapbook.site;

import ch.ilv.scrapbook.base.MessageResponse;
import ch.ilv.scrapbook.security.Roles;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
public class SiteController {

    private final SiteService siteService;

    SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @GetMapping("api/site")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Site>> all() {
        List<Site> result = siteService.getSites();
        return new ResponseEntity<> (result, HttpStatus.OK);
    }
    @GetMapping("api/site/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Site> one(@PathVariable Long id) {
        Site site = siteService.getSite(id);
        return new ResponseEntity<>(site, HttpStatus.OK);
    }

    @PostMapping("api/site")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Site> newSite(@Valid @RequestBody Site site) {
        Site savedSite = siteService.insertSite(site);
        return new ResponseEntity<>(savedSite, HttpStatus.OK);
    }

    @PutMapping("api/site/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Site> updateSite(@Valid @RequestBody Site site, @PathVariable Long id) {
        Site savedSite = siteService.updateSite(site, id);

        return new ResponseEntity<>(savedSite, HttpStatus.OK);
    }

    @DeleteMapping("api/site/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<MessageResponse> deleteSite(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(siteService.deleteSite(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
