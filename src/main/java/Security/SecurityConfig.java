package Security;

import Database.Account;
import Repos.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private AccountRepo accountRepository;
    private final MeinOAuth2UserService meinOAuth2UserService;

    public SecurityConfig(MeinOAuth2UserService meinOAuth2UserService) {
        this.meinOAuth2UserService = meinOAuth2UserService;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf->csrf.disable())
            .securityMatcher("/api/**")
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer((oauth2) -> oauth2
                .jwt(jwt -> jwt
                    .jwtAuthenticationConverter(this::jwtAuthenticationConverter)
                )
            );
        return http.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
            .requestMatchers("/login").permitAll()
            .requestMatchers("/images").permitAll()
            .requestMatchers("/admin").hasAuthority("ADMIN")
            .requestMatchers("/error/**").permitAll()
            .anyRequest().authenticated()

        ).oauth2Login((oauth2Login) -> oauth2Login
            .defaultSuccessUrl("/", true)
            .userInfoEndpoint((userInfo) -> userInfo
            .userService(meinOAuth2UserService)
        )
        );

        return http.build();
    }

    private AbstractAuthenticationToken jwtAuthenticationConverter(Jwt jwtToken) {

        String username = jwtToken.getClaimAsString("name");
        String email = jwtToken.getClaimAsString("email");

        Account account = accountRepository.findByUsername(username);

        if (account == null) {
            account = new Account(username);
            account.setEmail(email);
            //account.setAdmin(false);
            account.setAdmin(0);
            accountRepository.save(account);
        }

        boolean isAdmin = account.isAdmin();
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                username,
                null,
                Collections.singleton(() -> isAdmin ? "ROLE_ADMIN" : "ROLE_USER")
        );
        auth.setDetails(email);

        return auth;
    }
}
