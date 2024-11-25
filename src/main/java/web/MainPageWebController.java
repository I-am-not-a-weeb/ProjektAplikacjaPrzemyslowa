package web;

import Database.Meme;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import Services.MemeService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MainPageWebController {
    @Autowired
    private MemeService memeService;

    @GetMapping
    public String showMainPage(
            @AuthenticationPrincipal OAuth2User oauthUser,
            @RequestParam(value="page",required = false) String page,
            Model model,
            HttpServletRequest request
    ) {
        if(page != null)
        {
            Page<Meme> memes = memeService.getAllMemesByCreationDateDescByPage(PageRequest.of(Integer.parseInt(page), 10));
            if(memes.isEmpty())
            {
                return "NotFound";
            }

            model.addAttribute("next_page",Integer.parseInt(page)+1);
            model.addAttribute("memes", memes);
            return "mainPage";
        }

        model.addAttribute("next_page",1);
        model.addAttribute("memes", memeService.getAllMemesByCreationDateDescByPage(PageRequest.of(0, 10)));
        return "mainPage";
    }

}
