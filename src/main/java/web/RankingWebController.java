package web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import Services.RankingService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RankingWebController {
    @Autowired
    private RankingService rankingService;

    @RequestMapping("/ranking")
    public String showRankingPage(Model model)
    {

        model.addAttribute("accounts", rankingService.rankAccounts());
        model.addAttribute("memes", rankingService.rankMemes());
        model.addAttribute("comments", rankingService.rankComments());
        return "rankings";
    }
}
