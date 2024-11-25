package api.Controllers;

import Database.Account;
import Database.Notification;
import Services.AccountService;
import Services.NotificationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private AccountService accountService;

    @GetMapping("/{id}")
    public Notification getNotification(
            @PathVariable long id,
            HttpServletResponse response
    ) {
        Notification notification = notificationService.getById(id);
        if(notification == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return notification;
    }

    @PostMapping("/{username}")
    public void getNotification(
            @PathVariable String username,
            @RequestParam String message,
            @RequestParam(required = false) String relatedUrl,
            HttpServletResponse response
    ) {
        Account account = accountService.getAccountByUsername(username);
        if(account == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Notification notification = new Notification();
        notification.setRecipient(account);
        notification.setMessage(message);
        notification.setRelatedUrl(relatedUrl);

        notificationService.addNotification(notification, account);
    }
}
