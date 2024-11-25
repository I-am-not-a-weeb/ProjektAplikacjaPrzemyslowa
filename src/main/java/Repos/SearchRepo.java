package Repos;

import Database.Meme;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SearchRepo {
    @Autowired
    private EntityManager entityManager;

    public List<?> searchByTags(List<String> tags) {
        return entityManager.createQuery("SELECT m FROM Meme m JOIN m.tags t WHERE t IN :tags")
                .setParameter("tags", tags)
                .getResultList();
    }

    public Page<?> searchByTags(List<String> tags, PageRequest pageRequest) {
        List returned_list = entityManager.createQuery("SELECT m FROM Meme m JOIN m.tags t WHERE t.name IN :tags")
                .setParameter("tags", tags)
                .setFirstResult((pageRequest.getPageNumber()) * pageRequest.getPageSize())
                .setMaxResults(pageRequest.getPageSize())
                .getResultList();
        return new PageImpl<>(returned_list, pageRequest, returned_list.size());
    }
}
