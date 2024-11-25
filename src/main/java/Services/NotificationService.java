package Services;

import Database.Account;
import Database.Notification;
import Repos.AccountRepo;
import Repos.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class NotificationService {
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private NotificationRepo notificationRepo;


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Notification getById(long id) {
        return notificationRepo.findById(id);
    }

    @PreAuthorize("#username==authentication.principal")
    public Set<Notification> getByRecipientUsername(String username) {
        Account account = accountRepo.findByUsername(username);
        return account.getNotifications();
    }

    public void addNotification(
            Notification notification,
            Account recipient
    ) {
        recipient.addNotification(notification);
        notificationRepo.save(notification);
    }

}
