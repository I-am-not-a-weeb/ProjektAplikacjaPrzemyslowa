package Services;


import neo4j.neo4jMappings.Account;
import neo4j.neo4jMappings.Comment;
import neo4j.neo4jRepos.AccountRepo;
import neo4j.neo4jRepos.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;


@Service
public class AccountService {
    @Autowired private AccountRepo accountRepo;
    @Autowired private CommentRepo commentRepo;

    @Transactional public Account createAccount(String username) {
        Account account = new Account();
        account.setUsername(username);
        accountRepo.save(account);

        return account;
    }


}
