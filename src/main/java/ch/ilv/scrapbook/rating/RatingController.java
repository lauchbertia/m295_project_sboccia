package ch.ilv.scrapbook.rating;

import ch.ilv.scrapbook.base.MessageResponse;
import ch.ilv.scrapbook.comment.Comment;
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
public class RatingController {
    private final RatingService ratingService;

    RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("api/rating")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Rating>> all() {
        List<Rating> result = ratingService.getRatings();
        return new ResponseEntity<> (result, HttpStatus.OK);
    }
    @GetMapping("api/rating/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Rating> one(@PathVariable Long id) {
        Rating rating = ratingService.getRating(id);
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }

    @PostMapping("api/rating")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Rating> newRating(@Valid @RequestBody Rating rating) {
        Rating savedRating = ratingService.insertRating(rating);
        return new ResponseEntity<>(savedRating, HttpStatus.OK);
    }

    @PutMapping("api/rating/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Rating> updateRating(@Valid @RequestBody Rating rating, @PathVariable Long id) {
        Rating savedRating = ratingService.updateRating(rating, id);
        return new ResponseEntity<>(savedRating, HttpStatus.OK);
    }

    @DeleteMapping("api/rating/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<MessageResponse> deleteRating(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(ratingService.deleteRating(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
