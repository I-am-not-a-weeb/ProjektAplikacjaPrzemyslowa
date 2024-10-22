package web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import Services.MemeService;
@Controller
@RequestMapping("/")
public class MainPageWebController {
    @Autowired
    private MemeService memeService;

    @GetMapping
    public String showMainPage(
            @AuthenticationPrincipal OAuth2User oauthUser,
            Model model,
            HttpServletRequest request
    ) {


        model.addAttribute("memes", memeService.getAllMemesByPage(PageRequest.of(0, 10)));
        return "mainPage";
    }

}
