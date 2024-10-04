package Services;


import database.neo4j.Account;
import database.repositories.AccountRepo;
import database.repositories.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;


@Service
public class AccountService {
    @Autowired private AccountRepo accountRepo;
    @Autowired private CommentRepo commentRepo;

    @Transactional public Account createAccount(String username, String email) {
        Account account = new Account();
        account.setUsername(username);
        account.setEmail(email);
        accountRepo.save(account);
        return account;
    }


}
