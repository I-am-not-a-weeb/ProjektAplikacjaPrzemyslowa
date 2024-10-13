package Controllers;


import Database.Account;
import Services.AccountService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/add")
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
    public String getLikedMemes(@PathVariable String username) {
        return accountService.getAccountByUsername(username).getLikedMemes().toString();
    }
}
