package database.repositories;

//import database.neo4j.Account;
import database.mysql.Account;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AccountRepo extends PagingAndSortingRepository<Account, Long>, CrudRepository<Account, Long> {

    Account findByUsername(String username);

    Account findByEmail(String email);
}
