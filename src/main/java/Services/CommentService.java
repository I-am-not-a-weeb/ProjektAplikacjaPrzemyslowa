package Services;


import neo4j.neo4jRepos.CommentRepo;

import org.springframework.beans.factory.annotation.Autowired;
import neo4j.neo4jMappings.Account;
import neo4j.neo4jMappings.Comment;
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
