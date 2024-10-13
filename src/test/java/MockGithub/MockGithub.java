package MockGithub;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MockGithub {
}

class WithMockGitHubUserSecurityContextFactory implements WithSecurityContextFactory<WithMockGitHubUser> {

    @Override
    public GitHubUserSecurityContext createSecurityContext(WithMockGitHubUser withMockGitHubUser) {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("login", withMockGitHubUser.username());  // GitHub username comes under "login"
        for (String attribute : withMockGitHubUser.attributes()) {
            String[] keyValue = attribute.split(":");
            attributes.put(keyValue[0], keyValue[1]);
        }
        return new GitHubUserSecurityContext(new MockOAuth2User(attributes));
    }
}

class GitHubUserSecurityContext extends org.springframework.security.core.context.SecurityContextImpl {
    public GitHubUserSecurityContext(OAuth2User oauth2User) {
        super((Authentication) new DefaultOAuth2User(
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")),
                oauth2User.getAttributes(), "login"));
    }
}

class MockOAuth2User implements OAuth2User {

    private final Map<String, Object> attributes;

    public MockOAuth2User(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getName() {
        return (String) attributes.get("login");  // GitHub OAuth2 returns the username under "login"
    }
}
