package com.study.mvc.myboard.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

@Configuration
@EnableWebSecurity // Spring Security 활성화
public class SecurityConfig {

    //*
    // 비밀번호 암호화 인코더
    // UserServiceImpl에서 주입받아 암호화 할것
    // */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // Spring Security 메인 보안 설정
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. CSRF(Cross-Site Request Forgery) 보호 비활성화
                .csrf(AbstractHttpConfigurer::disable)

                // 2. HTTP 요청에 대한 접근 권한 설정
                .authorizeHttpRequests(authorize -> authorize

                        //  회원가입 API (/api/user/register)는 누구나 접근 가능
                        .requestMatchers(HttpMethod.POST, "/api/user/register").permitAll()

                        //  로그인 API (/api/user/login)는 누구나 접근 가능
                        .requestMatchers(HttpMethod.POST, "/api/user/login").permitAll()

                        //  게시글 목록/상세보기 (GET /api/board/...)는 누구나 접근 가능
                        .requestMatchers(HttpMethod.GET, "/api/board/**").permitAll()

                        //  그 외의 모든 요청은 '인증(로그인)'된 사용자만 접근 가능
                        .anyRequest().authenticated()
                )

                // 3. 폼 기반 로그인 설정
                .formLogin(form -> form
                        // Spring Security가 로그인 요청을 처리할 URL
                        .loginProcessingUrl("/api/user/login")

                        // 프론트엔드에서 'id'로 보내는 파라미터를 Spring Security가 'username'으로 인식
                        .usernameParameter("id")
                        // 프론트엔드에서 'password'로 보내는 파라미터
                        passwordEncoder().passwordParameter("password")

                        // 로그인 성공 시 (JSON 응답을 위해)
                        .successHandler((request, response, authentication) -> {
                            response.setStatus(HttpStatus.OK.value());
                            response.getWriter().write("{\"message\": \"로그인 성공\"}");
                        })
                        // 로그인 실패 시 (JSON 응답을 위해)
                        .failureHandler((request, response, exception) -> {
                            response.setStatus(HttpStatus.UNAUTHORIZED.value());
                            response.getWriter().write("{\"message\": \"아이디 또는 비밀번호가 올바르지 않습니다.\"}");
                        })
                )

                // 4. 로그아웃 설정 (필요시)
                .logout(logout -> logout
                        .logoutUrl("/api/user/logout") // 로그아웃 URL
                        .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)) // 로그아웃 성공 시 200 OK
                );

        return http.build();
    }
}