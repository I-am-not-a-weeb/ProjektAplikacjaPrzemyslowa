package neo4j.neo4jRepos;

import neo4j.neo4jMappings.Account;
import neo4j.neo4jMappings.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CommentRepo extends PagingAndSortingRepository<Comment, Long>, CrudRepository<Comment, Long> {


}

