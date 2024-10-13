import Database.Account;
import Repos.AccountRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;


import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

//@SpringBootTest(classes=SpringBootConfiguration.class)
@DataJpaTest
@EnableAutoConfiguration
@ContextConfiguration(classes = {AccountRepo.class})

//@SpringBootTest
public class AccountRepoTests {


    private AccountRepo accountRepo;

    private AccountRepoTests() {
    }


    @Test
    public void addAccountTest() {
        Account account = new Account();
        account.setUsername("test");
        accountRepo.save(account);
        assert(accountRepo.findByUsername("test") != null);
    }
}
