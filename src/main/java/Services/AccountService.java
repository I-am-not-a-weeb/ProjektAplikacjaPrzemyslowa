package Services;

import Database.Account;
import Repos.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountService {
    @Autowired
    private final AccountRepo accountRepo;

    public AccountService() {
        this.accountRepo = null;
    }
    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public boolean addAccount(Account account) {
        if(account == null ||
                accountRepo.findByUsername(account.getUsername()) != null ||
                accountRepo.findByEmail(account.getEmail()) != null
        ) {
            return false;
        }
        try{
            accountRepo.save(account);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Account getAccountByUsername(String username) {
        try{
            Account found = accountRepo.findByUsername(username);
            return found;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Account getAccountByEmail(String email) {
        try{
            Account found = accountRepo.findByEmail(email);
            return found;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //public boolean addLikedMeme(String username) {
    //
    //}
}
