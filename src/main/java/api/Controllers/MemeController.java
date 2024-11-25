package api.Controllers;

import Database.Account;
import Database.Comment;
import Database.Meme;
import Database.Tag;
import Services.AccountService;
import Services.CommentService;
import Services.MemeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Set;


@RestController
@RequestMapping("/api/meme")
public class MemeController {
    @Autowired
    private MemeService memeService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CommentService commentService;

    @PostMapping
    public String addMeme(
            @RequestParam String title,
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "tags", required = false) String[] tags,
            HttpServletResponse response
    )
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Account account = accountService.getAccountByUsername(username);

        if(account == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "Author not found";
        }

        Meme meme = new Meme();
        meme.setAuthorMeme(account);
        meme.setTitle(title);
        meme.setCreationDate(new Date());

        for(String tag : tags) {
            meme.addTag(new Tag(tag));
        }
        memeService.addMemeFile(file, meme);
        return "Meme added";
    }
    @GetMapping("/{id}")
    @ResponseBody
    public Meme getMeme(
            @PathVariable Long id
            ) {
        return memeService.getMemeById(id);
    }

    @PostMapping("/{id}/like")
    public String likeMeme(
            @PathVariable Long id,
            HttpServletResponse response
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

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
    public int getNumOfLikes(
            @PathVariable Long id,
            HttpServletResponse response
    )
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
            @RequestParam String content,
            HttpServletResponse response
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

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

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setAuthorComment(account);
        comment.setMemeCommented(meme);
        meme.addComment(comment);
        commentService.save(comment);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }
    @GetMapping("/{id}/comments")
    public Set<Comment> getComments(
            @PathVariable Long id,
            HttpServletResponse response
    ) {
        Meme meme = memeService.getMemeById(id);
        if(meme == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return meme.getComments();
    }
}
