package web;

import Database.Account;
import Database.Comment;
import Database.Meme;
import Services.AccountService;
import Services.CommentService;
import Services.MemeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping("/meme")
public class MemeWebController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private MemeService memeService;
    @Autowired
    private CommentService commentService;

    @PostMapping
    public String addMeme(@ModelAttribute Meme meme,
                          @RequestParam(name = "file",required = false) MultipartFile file,
                          @AuthenticationPrincipal OAuth2User oauthUser,
                          HttpServletResponse response) {
        String username = oauthUser.getAttribute("login");
        Account account = accountService.getAccountByUsername(username);

        meme.setAuthorMeme(account);
        meme.setCreationDate(new Date());
        memeService.addMemeFile(file, meme);

        response.setStatus(HttpServletResponse.SC_CREATED);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String showMemePage(@PathVariable Long id,
                               @AuthenticationPrincipal OAuth2User oauthUser,
                               //HttpServletRequest request,
                               Model model) {
        Meme meme = memeService.getMemeById(id);

        if (meme == null) {
            return "NotFound";
        }
        //String username = oauthUser.getAttribute("login");

        model.addAttribute("newComment",new Comment());
        //model.addAttribute("username", username);
        model.addAttribute("meme", meme);
        return "meme";
    }

    @PostMapping("/{id}/comment")
    public String addCommentToMeme(@PathVariable Long id,
                                 @ModelAttribute Comment comment,
                                 @AuthenticationPrincipal OAuth2User oauthUser,
                                 HttpServletResponse response
    ) {
        String username = oauthUser.getAttribute("login");
        Account account = accountService.getAccountByUsername(username);

        comment.setAuthorComment(account);
        comment.setMemeCommented(memeService.getMemeById(id));
        memeService.getMemeById(id).addComment(comment);

        commentService.save(comment);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return "redirect:/meme/" + id;
    }

    @PostMapping("/{id}/like")
    public String likeMeme(@PathVariable Long id,
                           @RequestHeader(value = HttpHeaders.REFERER, required = false) final String referrer,
                           @AuthenticationPrincipal OAuth2User oauthUser,
                           HttpServletResponse response) {
        String username = oauthUser.getAttribute("login");
        Account account = accountService.getAccountByUsername(username);

        Meme meme = memeService.getMemeById(id);
        memeService.accountLikeMeme(meme, account);


        response.setStatus(HttpServletResponse.SC_CREATED);
        return "redirect:" + referrer;
    }
}