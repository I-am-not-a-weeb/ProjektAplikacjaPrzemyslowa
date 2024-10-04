package Services;

import org.springframework.stereotype.Service;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;

//import database.neo4j.Account;
import database.mysql.Account;
import database.repositories.AccountRepo;

@Service
public class MeinOAuthService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>  {

    private final AccountRepo accountRepo;

    public MeinOAuthService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest){
        OAuth2User oauth2User = new DefaultOAuth2UserService().loadUser(userRequest);
        String username = oauth2User.getAttribute("login");

        if (username != null && accountRepo.findByUsername(username) == null) {
            Account account = new Account();
            account.setUsername(username);
            accountRepo.save(account);
        }
        return oauth2User;
    }
}
