package api.Controllers;


import Database.Comment;
import Services.AccountService;
import Services.CommentService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    public CommentService commentService;
    @Autowired
    private AccountService accountService;

    @GetMapping("/{id}")
    Comment getCommentById(
            @PathVariable Long id,
            HttpServletResponse response
    ) {
        Comment comment = commentService.getCommentById(id);
        if(comment == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return comment;
    }

    @GetMapping("/{id}/children")
    Set<Long> getChildrenComments(
            @PathVariable Long id,
            HttpServletResponse response
    ) {
        Comment comment = commentService.getCommentById(id);
        if(comment == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return comment.getChildrenComments();
    }
}
