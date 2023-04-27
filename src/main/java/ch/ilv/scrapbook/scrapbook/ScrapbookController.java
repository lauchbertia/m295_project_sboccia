package ch.ilv.scrapbook.scrapbook;

import ch.ilv.scrapbook.base.MessageResponse;
import ch.ilv.scrapbook.scrapbook.Scrapbook;
import ch.ilv.scrapbook.comment.CommentService;
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
public class ScrapbookController {
    private final ScrapbookService scrapbookService;

    ScrapbookController(ScrapbookService scrapbookService) {
        this.scrapbookService = scrapbookService;
    }

    @GetMapping("api/scrapbook")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Scrapbook>> all() {
        List<Scrapbook> result = scrapbookService.getScrapbooks();
        return new ResponseEntity<> (result, HttpStatus.OK);
    }
    @GetMapping("api/scrapbook/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Scrapbook> one(@PathVariable Long id) {
        Scrapbook scrapbook = scrapbookService.getScrapbook(id);
        return new ResponseEntity<>(scrapbook, HttpStatus.OK);
    }

    @PostMapping("api/scrapbook")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Scrapbook> newScrapbook(@Valid @RequestBody Scrapbook scrapbook) {
        Scrapbook savedScrapbook = scrapbookService.insertScrapbook(scrapbook);
        return new ResponseEntity<>(savedScrapbook, HttpStatus.OK);
    }

    @PutMapping("api/scrapbook/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Scrapbook> updateScrapbook(@Valid @RequestBody Scrapbook scrapbook, @PathVariable Long id) {
        Scrapbook savedScrapbook = scrapbookService.updateScrapbook(scrapbook, id);
        return new ResponseEntity<>(savedScrapbook, HttpStatus.OK);
    }

    @DeleteMapping("api/scrapbook/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<MessageResponse> deleteScrapbook(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(scrapbookService.deleteScrapbook(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
