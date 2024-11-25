package Security;

import Database.Account;
import Repos.AccountRepo;
import Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


public class MeinOAuth2User implements OAuth2User {
    private OAuth2User oauth2User;
    private Account account;

    public MeinOAuth2User(OAuth2User oauth2User, Account account) {
        this.oauth2User = oauth2User;
        this.account = account;
    }
    @Override
    public Map<String, Object> getAttributes() {
        return oauth2User.getAttributes();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (account.isAdmin()) {
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        }

        authorities.add(new SimpleGrantedAuthority("USER"));
        return authorities;
    }
    @Override
    public String getName() {
        return oauth2User.getName();
    }

    public boolean isAdmin()
    {
        return account.isAdmin();
    }
}
