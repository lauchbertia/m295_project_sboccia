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

    ScrapbookController(ScrapbookService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("api/comments")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Scrapbook>> all() {
        List<Scrapbook> result = commentService.getScrapbooks();
        return new ResponseEntity<> (result, HttpStatus.OK);
    }
    @GetMapping("api/comment/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Scrapbook> one(@PathVariable Long id) {
        Scrapbook comment = commentService.getScrapbook(id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PostMapping("api/comment")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Scrapbook> newScrapbook(@Valid @RequestBody Scrapbook comment) {
        Scrapbook savedScrapbook = scrapbookService.insertScrapbook(comment);
        return new ResponseEntity<>(savedScrapbook, HttpStatus.OK);
    }

    @PutMapping("api/comment/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Scrapbook> updateScrapbook(@Valid @RequestBody Scrapbook comment, @PathVariable Long id) {
        Scrapbook savedScrapbook = commentService.updateScrapbook(comment, id);
        return new ResponseEntity<>(savedScrapbook, HttpStatus.OK);
    }

    @DeleteMapping("api/comment/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<MessageResponse> deleteScrapbook(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(commentService.deleteScrapbook(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
