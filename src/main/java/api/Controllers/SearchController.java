package api.Controllers;

import Database.Meme;
import Services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping
    public Page<?> showSearchPage(
            @RequestParam(value="tags") String[] tags,
            @RequestParam(value="page",required = false) String page
    ) {
        PageRequest requestedPage;

        if (page != null) {
            requestedPage = PageRequest.of(Integer.parseInt(page), 10);
        } else {
            requestedPage = PageRequest.of(0, 10);
        }
        Page<?> memes = searchService.searchByTags(Arrays.asList(tags), requestedPage);

        return memes;
    }
}
