package ch.ilv.scrapbook.comment;

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

public class CommentController {
    private final CommentService commentService;

    CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("api/comments")
    @RolesAllowed(Roles.Read)
    public ResponseEntity <List<Comment>> all() {
        List<Comment> result = commentService.getComments();
        return new ResponseEntity<> (result, HttpStatus.OK);
    }
    @GetMapping("api/comment/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Comment> one(@PathVariable Long id) {
        Comment comment = commentService.getComment(id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PostMapping("api/comment")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Comment> newComment(@Valid @RequestBody Comment comment) {
        Comment savedComment = commentService.insertComment(comment);
        return new ResponseEntity<>(savedComment, HttpStatus.OK);
    }

    @PutMapping("api/comment/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Comment> updateComment(@Valid @RequestBody Comment comment, @PathVariable Long id) {
        Comment savedComment = commentService.updateComment(comment, id);
        return new ResponseEntity<>(savedComment, HttpStatus.OK);
    }

    @DeleteMapping("api/comment/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<MessageResponse> deleteComment(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(commentService.deleteComment(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
