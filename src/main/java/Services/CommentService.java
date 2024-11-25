package Services;


import Database.Account;
import Database.Comment;
import Repos.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class CommentService {
    @Autowired
    private CommentRepo commentRepo;

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

    @Transactional
    public void accountLikeComment(Comment comment, Account account) {
        comment.addLikingAccount(account);
        account.addLikedComment(comment);
    }
    public void save(Comment comment) {
        commentRepo.save(comment);
    }

    public void setCommentRepo(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }
}
