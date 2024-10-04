package Services;


import database.repositories.CommentRepo;

import org.springframework.beans.factory.annotation.Autowired;
import database.neo4j.Comment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {
    @Autowired private CommentRepo commentRepo;

    @Transactional
    public void createComment(String content) {
        Comment comment = new Comment();
        comment.setContent(content);
        commentRepo.save(comment);
    }
}
