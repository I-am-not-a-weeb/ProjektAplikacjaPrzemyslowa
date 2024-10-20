package web;

import Database.Account;
import Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccountWebController {
    @Autowired
    private AccountService accountService;

    @RequestMapping("/account/{username}")
    public String showAccountPage(@PathVariable String username, Model model)
    {
        Account account = accountService.getAccountByUsername(username);

        if(account == null) {
            return "NotFound";
        }
        model.addAttribute("account", account);
        return "account";
    }
}
