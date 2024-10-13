package Controllers;

import Database.Account;
import Database.Meme;
import Database.Tag;
import Services.AccountService;
import Services.MemeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/meme")
public class MemeController {
    @Autowired
    private MemeService memeService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/{id}")
    @ResponseBody
    public Meme getMeme(@PathVariable Long id) {
        return memeService.getMemeById(id);
    }

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


}
