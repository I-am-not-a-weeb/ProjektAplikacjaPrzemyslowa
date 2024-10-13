package Services;


import Database.Tag;
import Repos.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TagService {
    @Autowired
    private final TagRepo tagRepo;

    public TagService() {
        this.tagRepo = null;
    }
    public TagService(TagRepo tagRepo) {
        this.tagRepo = tagRepo;
    }

    public Tag getTag(String name) {
        Tag tag = tagRepo.findByName(name);
        if(tag==null)
        {
            tag = new Tag(name);
            tagRepo.save(tag);
            return tag;
        }
        else
        {
            return tag;
        }
    }
    public List<Tag> getAll() {
        return tagRepo.findAll();
    }
}
