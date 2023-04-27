package ch.ilv.scrapbook.comment;

import ch.ilv.scrapbook.base.MessageResponse;
import ch.ilv.scrapbook.dataaccess.EntityNotFoundException;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository repository;
    public CommentService(CommentRepository repository) {
        this.repository = repository;
    }
    public List<Comment> getComments() {
      return repository.findAll();
    }

    public Comment getComment(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Comment.class));
    }

    public Comment insertComment(Comment comment) {
        return repository.save(comment);
    }

    public Comment updateComment(Comment comment, Long id) {
        return repository.findById(id)
                .map(commentOrig -> {
                    commentOrig.setContent(comment.getContent());
                    return repository.save(commentOrig);
                })
                .orElseGet(() -> repository.save(comment));
    }

    public MessageResponse deleteComment(Long id) {
        repository.deleteById(id);
        return new MessageResponse("Comment" + id + " deleted");
    }
}
