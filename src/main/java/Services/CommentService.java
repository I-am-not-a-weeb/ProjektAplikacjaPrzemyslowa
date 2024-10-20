package Services;


import Database.Comment;
import Repos.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CommentService {
    @Autowired
    CommentRepo commentRepo;

    public CommentService() {
        this.commentRepo = null;
    }

    public CommentService(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    public Comment getCommentById(long id) {
        return commentRepo.findById(id);
    }

    public Set<Long> getChildrenComments(long id) {
        Comment comment = commentRepo.findById(id);
        return comment.getChildrenComments();
    }

    public void save(Comment comment) {
        commentRepo.save(comment);
    }
}
