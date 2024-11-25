package api.Controllers;


import Database.Account;
import Database.Comment;
import Database.Meme;
import Database.Notification;
import Services.AccountService;
import Services.NotificationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private NotificationService notificationService;

    /*@PostMapping
    public String addAccount(
            @RequestParam String username,
            @RequestParam String email,
            HttpServletResponse response) {
        if(username == null && email == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "Username and email cannot be null";
        }

        Account account = new Account();
        account.setUsername(username);
        account.setEmail(email);
        if(!accountService.addAccount(account))
        {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "Username of email already used";
        }
        response.setStatus(HttpServletResponse.SC_CREATED);
        return "Account added";
    }*/

    @GetMapping("/{username}")
    @ResponseBody
    public Account getAccount(
            @PathVariable String username,
            HttpServletResponse response
    ) {
        Account account = accountService.getAccountByUsername(username);
        if(account == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return account;
    }

    @GetMapping("/{username}/memes")
    @ResponseBody
    public Set<Meme> getMemes(
            @PathVariable String username,
            HttpServletResponse response
    ) {
        Account account = accountService.getAccountByUsername(username);
        if(account == null)
        {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return account.getAuthoredMemes();
    }

    @GetMapping("/{username}/likedMemes")
    @ResponseBody
    public Set<Meme> getLikedMemes(
            @PathVariable String username,
            HttpServletResponse response
    ) {
        Account account = accountService.getAccountByUsername(username);
        if(account == null)
        {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        return account.getLikedMemes();
    }

    @GetMapping("/{username}/comments")
    @ResponseBody
    public Set<Comment> getComments(
            @PathVariable String username,
            HttpServletResponse response
    ) {
        Account account = accountService.getAccountByUsername(username);
        if(account == null)
        {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return account.getAuthoredComments();
    }

    @GetMapping("/{username}/likedComments")
    @ResponseBody
    public Set<Comment> getLikedComments(
            @PathVariable String username,
            HttpServletResponse response
    ) {
        return accountService.getAccountByUsername(username).getLikedComments();
    }
    @PostMapping("/{username}/like")
    public void likeAccount(
            @PathVariable String username,
            HttpServletResponse response
    )
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String qUsername = authentication.getName();
        if(accountService.likeAccountByUsername(qUsername, username))
            response.setStatus(HttpServletResponse.SC_OK);
        else
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    @GetMapping("/{username}/likedAccounts")
    public Set<String> getLikedAccounts(@PathVariable String username) {
        return accountService.getAccountByUsername(username).getLikedAccountsUsernames();
    }

    @PutMapping("/{username}")
    public void updateAccount(
            @PathVariable String username,
            @RequestParam(value="email2", required = false) String email2,
            HttpServletResponse response
    )
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String qUsername = authentication.getName();

        // sprawdzamy czy to ten sam uzytkownik (przed zmiana na PreAuthorize)
        if(!qUsername.equals(username)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // sprawdzamy czy konto istnieje
        Account account = accountService.getAccountByUsername(username);
        if(account == null)
        {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // poczatek bloku zmieniana
        if(email2 != null)
            account.setEmail2(email2);


        // koniec bloku
        accountService.save(account);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @GetMapping("/{username}/notifications")
    public Set<Notification> getNotifications(
            @PathVariable String username,
            HttpServletResponse response
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String qUsername = authentication.getName();

        // sprawdzamy czy to ten sam uzytkownik (przed zmiana na PreAuthorize)
        //if(!qUsername.equals(username)) {
        //    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        //    return null;
        //}

        return notificationService.getByRecipientUsername(username);
    }
}
