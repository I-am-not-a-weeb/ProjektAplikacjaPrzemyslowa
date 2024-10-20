package api.Controllers;


import Database.Account;
import Database.Comment;
import Database.Meme;
import Services.AccountService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    public String addAccount(@RequestParam String username, @RequestParam String email, HttpServletResponse response) {
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
    }

    @GetMapping("/{username}")
    @ResponseBody
    public Account getAccount(@PathVariable String username) {
        Account found = accountService.getAccountByUsername(username);
        return found;
    }

    @GetMapping("/{username}/memes")
    @ResponseBody
    public Set<Meme> getMemes(@PathVariable String username) {
        return accountService.getAccountByUsername(username).getAuthoredMemes();
    }

    @GetMapping("/{username}/likedMemes")
    @ResponseBody
    public Set<Meme> getLikedMemes(@PathVariable String username) {
        return accountService.getAccountByUsername(username).getLikedMemes();
    }

    @GetMapping("/{username}/comments")
    @ResponseBody
    public Set<Comment> getComments(@PathVariable String username) {
        return accountService.getAccountByUsername(username).getAuthoredComments();
    }

    @GetMapping("/{username}/likedComments")
    @ResponseBody
    public Set<Comment> getLikedComments(@PathVariable String username) {
        return accountService.getAccountByUsername(username).getLikedComments();
    }
    @PostMapping("/{username}/like")
    public void likeAccount(@PathVariable String username, @RequestParam("username") String qUsername, HttpServletResponse response)
    {
        if(accountService.likeAccountByUsername(qUsername, username))
            response.setStatus(HttpServletResponse.SC_OK);
        else
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    @GetMapping("/{username}/likedAccounts")
    public Set<String> getLikedAccounts(@PathVariable String username) {
        return accountService.getAccountByUsername(username).getLikedAccountsUsernames();
    }

}
