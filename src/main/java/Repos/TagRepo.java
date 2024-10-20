package Repos;

import Database.Meme;
import Database.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TagRepo extends JpaRepository<Tag,Long> {
    Optional<Tag> findById(long id);
    Tag findByName(String name);

    Set<Tag> findAllByOrderByMemes();
}
