package Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2Error;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final MeinOAuth2UserService meinOAuth2UserService;

    public SecurityConfig(MeinOAuth2UserService meinOAuth2UserService) {
        this.meinOAuth2UserService = meinOAuth2UserService;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
            .requestMatchers("/login").permitAll()
                .requestMatchers("/images").permitAll()
            .anyRequest().authenticated()

        ).oauth2Login((oauth2Login) -> oauth2Login
            .defaultSuccessUrl("/", true)
            .userInfoEndpoint((userInfo) -> userInfo
            .userService(meinOAuth2UserService)
        )
        );

    //.formLogin((form) -> form
         //       .loginPage("/login")
         //       .permitAll()
        //)



        return http.build();
    }
}
