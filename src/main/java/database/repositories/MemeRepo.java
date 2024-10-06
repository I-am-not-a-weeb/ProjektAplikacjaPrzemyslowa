package database.repositories;

import java.util.HashSet;
import java.util.Set;

import database.mysql.Comment;
import database.mysql.Meme;
import database.mysql.Tags;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface MemeRepo extends PagingAndSortingRepository<Meme, Long>, CrudRepository<Meme, Long> {

    Meme findById(long id);

    @Query("SELECT t FROM Meme m JOIN m.tags t WHERE m.id = :id")
    HashSet<Tags> findTagsOfMemeByMemeId(Long id);

    @Query("SELECT m FROM Meme m WHERE :tag MEMBER OF m.tags")
    Set<Meme> findMemesByTags(Tags tag);

    @Query("SELECT COUNT(a) FROM Account a JOIN a.likedAccounts m WHERE m.id = :id")
    Long findNumberOfLikesById(long id);
}
