package Repos;


import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class AdminRepo {

    @Autowired
    EntityManager entityManager;

    public List<String> getAllTables() {
        return entityManager.createNativeQuery("SHOW TABLES").getResultList();
    }

    public Object getTableData(String tableName)
    {
        return entityManager.createNativeQuery("SELECT * FROM " + tableName).getResultList();
    }
}
