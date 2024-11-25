package Repos;

import Database.Comment;
import Database.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface NotificationRepo extends JpaRepository<Notification,Long> {

    Notification findById(long id);

    //Set<Notification> findAllByUsername(String username);
}
