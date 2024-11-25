package web;


import Database.Account;
import Database.Comment;
import Services.AccountService;
import Services.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class CommentWebController {
    @Autowired
    CommentService commentService;
    @Autowired
    private AccountService accountService;

    /*CommentWebController() {
        try{
            ApplicationContext context =
                    new ClassPathXmlApplicationContext(new String[] {"META-INF/beans.xml"});
            this.commentService = (CommentService)context.getBean("commentService");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }*/

    @PostMapping("/comment/{id}/like")
    public String likeComment(@PathVariable Long id,
                            @RequestHeader(value = HttpHeaders.REFERER, required = false) final String referrer,
                            @AuthenticationPrincipal OAuth2User oauthUser,
                            Model model,
                            HttpServletRequest request
    ) {
        String username = oauthUser.getAttribute("login");
        Account account = accountService.getAccountByUsername(username);
        Comment comment = commentService.getCommentById(id);
        commentService.accountLikeComment(comment, account);

        return "redirect:" + referrer;
    }

    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }
}
