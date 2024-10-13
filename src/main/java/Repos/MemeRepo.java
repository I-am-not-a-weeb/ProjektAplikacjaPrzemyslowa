package Repos;


import Database.Meme;

import Database.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface MemeRepo extends JpaRepository<Meme,Long> {

    Meme findById(long id);

    @Query("SELECT m FROM Meme m WHERE m.title LIKE %:keyword%")
    Optional<Meme> findByWordInTitle(@Param("keyword") String title);

    @Query("SELECT m.tags FROM Meme m WHERE m.id = :id")
    Set<Tag> findAllTagsByMemeId(@Param("id") long id);
}
