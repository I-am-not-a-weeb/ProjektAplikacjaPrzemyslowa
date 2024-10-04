package database.repositories;

import database.neo4j.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentRepo extends PagingAndSortingRepository<Comment, Long>, CrudRepository<Comment, Long> {


}

