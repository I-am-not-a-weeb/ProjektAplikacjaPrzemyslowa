package web;

import Database.Account;
import Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account/")
public class AccountWebController {
    @Autowired
    private AccountService accountService;

    @GetMapping( "/{username}")
    public String showAccountPage(
            @PathVariable String username,
            Model model
    )
    {
        Account account = accountService.getAccountByUsername(username);

        if(account == null) {
            return "NotFound";
        }
        model.addAttribute("account", account);
        return "account";
    }

    @PostMapping("/{username}/like")
    public String likeAccount(
            @PathVariable String username,
            @RequestHeader(value = HttpHeaders.REFERER, required = false) final String referrer,
            @AuthenticationPrincipal OAuth2User oauthUser,
            Model model)
    {
        Account likedAccount = accountService.getAccountByUsername(username);
        Account likingAccount = accountService.getAccountByUsername(oauthUser.getAttribute("login"));
        accountService.accountLikeAccount(likingAccount, likedAccount);
        return "redirect:" + referrer;
    }
}
