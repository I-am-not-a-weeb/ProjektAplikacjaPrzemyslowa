package api.Controllers;


import Database.Comment;
import Services.CommentService;
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

    @GetMapping("/{id}")
    Comment getCommentById(@PathVariable Long id) {
        return commentService.getCommentById(id);
    }

    @GetMapping("/{id}/children")
    Set<Long> getChildrenComments(@PathVariable Long id) {
        return commentService.getChildrenComments(id);
    }
}
