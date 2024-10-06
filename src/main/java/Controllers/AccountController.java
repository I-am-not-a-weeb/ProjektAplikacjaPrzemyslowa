package Controllers;

import org.springframework.web.bind.annotation.*;
import Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import database.mysql.Account;
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/")
    public String createAccount(@RequestParam("username") String username, @RequestParam("email") String email)
    {
        if(username == null || email == null)
        {
            return "Username or email must be provided";
        }
        accountService.createAccount(username,email);
        return "Account created";
    }

    @GetMapping("/{username}/")
    public String getAccount(@PathVariable String username) {

        Account acc = accountService.getAccountByUsername(username);

        if(acc == null)
        {
            return "Account not found";
        }
        return "Account found";
    }
}
