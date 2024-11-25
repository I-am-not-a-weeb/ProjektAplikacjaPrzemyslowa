package web;


import Database.Meme;
import Services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchWebController {
    @Autowired
    private SearchService searchService;

    @GetMapping
    public String showSearchPage(
            @RequestParam(value="tags",required = false) String[] tags,
            @RequestParam(value="page",required = false) String page,
            Model model
    ) {
        PageRequest requestedPage;

        if (page != null) {
            requestedPage = PageRequest.of(Integer.parseInt(page), 10);

            model.addAttribute("next_page", Integer.parseInt(page) + 1);
            return "searchPage";
        } else {
            requestedPage = PageRequest.of(0, 10);
            model.addAttribute("next_page", 1);
        }

        if (tags == null) {
            return "searchPage";
        }
        Page<?> memes = searchService.searchByTags(Arrays.asList(tags), requestedPage);

        model.addAttribute("memes", memes);
        return "searchPage";
    }
}
