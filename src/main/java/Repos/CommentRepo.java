package Repos;

import Database.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CommentRepo extends JpaRepository<Comment,Long> {

    Comment findById(long id);


    //Page<Comment> findAllPage(Pageable pageable);

    //@Query("SELECT c FROM Comment c ORDER BY c.likingAccounts.size DESC")
    Set<Comment> findAllByOrderByLikingAccountsDesc();
}
