package api.Controllers;

import Database.Meme;
import Database.Comment;
import Database.Account;
import Services.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@RestController
@RequestMapping("/api/ranking")
public class RankingController {
    @Autowired
    public RankingService rankingService;

    @GetMapping("/accounts")
    public Set<Account> rankAccounts() {
        return rankingService.rankAccounts();
    }

    @GetMapping("/memes")
    public Set<Meme> rankMemes() {
        return rankingService.rankMemes();
    }

    @GetMapping("/comments")
    public Set<Comment> rankComments() {
        return rankingService.rankComments();
    }

}
