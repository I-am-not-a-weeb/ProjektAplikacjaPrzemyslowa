package MockGithub;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockGitHubUserSecurityContextFactory.class)
public @interface WithMockGitHubUser {
    String username() default "githubUser";

    String[] attributes() default {};
}
