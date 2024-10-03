package Controllers;

import org.neo4j.driver.*;
import org.springframework.web.bind.annotation.*;
import Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/")
    public String createAccount() {
        accountService.createAccount("test");
        return "Account created";
    }

    @GetMapping("/{username}/")
    public String getAccount(@PathVariable String username) {

        return "Account details for " + username;
    }
}
