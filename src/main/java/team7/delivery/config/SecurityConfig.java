package team7.delivery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // TODO : USER URL, OWENER URL 설정하기
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users/signup", "/auth/signin").permitAll()  // 누구나 접근 가능
//                        .requestMatchers("/user/**").hasRole("USER") //
                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasic ->httpBasic.disable());// 그 외에는 인증된 사용자만 접근 가능

        return http.build();
    }

}
