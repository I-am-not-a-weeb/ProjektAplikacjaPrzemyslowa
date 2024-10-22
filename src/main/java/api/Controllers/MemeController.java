package api.Controllers;

import Database.Account;
import Database.Comment;
import Database.Meme;
import Database.Tag;
import Services.AccountService;
import Services.MemeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/api/meme")
public class MemeController {
    @Autowired
    private MemeService memeService;

    @Autowired
    private AccountService accountService;


    @PostMapping
    public String addMeme(
            @RequestParam String username,
            @RequestParam String title,
            @RequestParam String image_url,
            @RequestParam String[] tags,
            HttpServletResponse response)
    {
        Account author = accountService.getAccountByUsername(username);
        if(author == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "Author not found";
        }

        Meme meme = new Meme(title, image_url, author);

        for(String tag : tags) {
            meme.addTag(new Tag(tag));
        }
        //meme.setTags(tags);
        memeService.addMeme(meme);
        return "Meme added";
    }
    @GetMapping("/{id}")
    @ResponseBody
    public Meme getMeme(@PathVariable Long id) {
        return memeService.getMemeById(id);
    }

    @PostMapping("/{id}/like")
    public String likeMeme(@PathVariable Long id, @RequestParam String username, HttpServletResponse response) {

        Account account = accountService.getAccountByUsername(username);
        if(account == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "Account not found";
        }

        Meme meme = memeService.getMemeById(id);
        if(meme == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "Meme not found";
        }

        memeService.accountLikeMeme(meme, account);
        return "Meme liked";
    }
    @GetMapping("/{id}/likes")
    public int getNumOfLikes(@PathVariable Long id, HttpServletResponse response)
    {
        Meme meme = memeService.getMemeById(id);
        if(meme == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return -1;
        }
        return meme.getLikingAccounts().size();
    }

    @PostMapping("/{id}/comment")
    public void addCommentToMeme(
            @PathVariable Long id,
            @RequestParam String username,
            @RequestParam String content,
            @ModelAttribute Comment comment,
            @AuthenticationPrincipal OAuth2User oauthUser,
            HttpServletResponse response
            ) {
        Account account = accountService.getAccountByUsername(username);
        if(account == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Meme meme = memeService.getMemeById(id);
        if(meme == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //meme.addComment(new Comment(account,content));
    }
    @GetMapping("/{id}/comments")
    public Set<Comment> getComments(@PathVariable Long id, HttpServletResponse response) {
        Meme meme = memeService.getMemeById(id);
        if(meme == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return meme.getComments();
    }
}
