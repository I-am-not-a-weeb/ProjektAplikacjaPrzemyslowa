package Security;

import Database.Account;
import Repos.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class MeinOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Autowired
    private AccountRepo accountRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oauth2User = new DefaultOAuth2UserService().loadUser(userRequest);

        String username = oauth2User.getAttribute("login");

        if (username != null && accountRepository.findByUsername(username) == null) {
            Account account = new Account(username);
            account.setEmail(oauth2User.getAttribute("email"));
            accountRepository.save(account);
        }
        return new MeinOAuth2User(oauth2User, accountRepository.findByUsername(username));
    }
}
