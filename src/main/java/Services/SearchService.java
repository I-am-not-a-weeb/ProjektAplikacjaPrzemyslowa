package Services;


import Repos.SearchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    @Autowired
    private SearchRepo searchRepo;

    public Page<?> searchByTags(List<String> tags, PageRequest pageRequest) {
        return searchRepo.searchByTags(tags, pageRequest);
    }
    public List<? > searchByTags(List<String> tags) {
        return searchRepo.searchByTags(tags);
    }


}
