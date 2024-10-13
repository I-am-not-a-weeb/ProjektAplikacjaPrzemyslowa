package Repos;


import Database.Account;
import Database.Meme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

    Account findByUsername(String username);

    Account findByEmail(String email);
    Set<Account> findAllLikedAccountsByUsername(String username);
    @Query("SELECT m FROM Meme m WHERE m.authorMeme.username = :username")
    Set<Meme> findAllLikedMemesByUsername(@Param("username") String username);



}
