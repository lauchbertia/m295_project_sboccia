package ch.ilv.scrapbook;

import ch.ilv.scrapbook.comment.Comment;
import ch.ilv.scrapbook.comment.CommentRepository;
import ch.ilv.scrapbook.comment.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CommentServiceTest {

    private CommentService commentService;
    private final CommentRepository commentRepositoryMock = mock(CommentRepository.class);

    private final Comment commentMock = mock(Comment.class);

    @BeforeEach
    void setUp() {
        commentService = new CommentService(commentRepositoryMock);
    }

    @Test
    void createComment() {
        when(commentRepositoryMock.save(commentMock)).thenReturn(commentMock);
        commentService.insertComment(commentMock);
        verify(commentRepositoryMock, times(1)).save(any());
    }

   /* @Test
    void findComment() {
        when(commentService.getComments()).thenReturn((List<Comment>) commentMock);
        Comment c = commentService.getComment(any());
        verify(commentRepositoryMock, times(1)).findById(any());
    }*/

    @Test
    void deleteComment() {
        commentService.deleteComment(any());
        verify(commentRepositoryMock, times(1)).deleteById(any());
    }

    /*@Test
    void updateComment() {
        Long id = 52l;
        commentService.updateComment(any(), 52l);
        verify(commentRepositoryMock, times(1)).deleteById(id);
    }*/
}
