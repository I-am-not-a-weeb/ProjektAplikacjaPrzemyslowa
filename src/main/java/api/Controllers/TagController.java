package api.Controllers;

import Database.Tag;
import Services.TagService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {
    @Autowired
    private final TagService tagService;

    public TagController() {
        this.tagService = null;
    }
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/all")
    public List<Tag> getAllTags(
            HttpServletResponse response
    ) {
        List<Tag> tags = tagService.getAll();

        if(tags == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return tags;
    }
}
