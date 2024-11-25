package api.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/report")
public class ReportController {
    @PostMapping("/account/{username}")
    public String reportAccounts(
            @PathVariable String username
    ) {
        return "Report accounts";
    }

    @PostMapping("/meme")
    public String reportMemes(

    ) {
        return "Report memes";
    }

    @PostMapping("/comment")
    public String reportComments(

    ) {
        return "Report comments";
    }
}
