package neo4j.neo4jRepos;

import neo4j.neo4jMappings.Account;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;

//import java.util.List;


public interface AccountRepo extends PagingAndSortingRepository<Account, Long>, CrudRepository<Account, Long> {
    //List<Account> findByUsername(@Param("username")String username);
    //void deleteByUsername(@Param("username")String username);
}
