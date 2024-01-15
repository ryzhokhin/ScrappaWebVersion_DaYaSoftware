package com.DaYaSoftware.ScrappaWebVersion.configurations;

import com.DaYaSoftware.ScrappaWebVersion.services.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import static org.springframework.security.config.Customizer.withDefaults;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


//@EnableWebSecurity
//@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/", "/registration")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                ).formLogin(
//                        Customizer.withDefaults()
                        (form) -> form
                                .loginPage("/login").permitAll()
                )
                .sessionManagement(session -> session // SESSION MANAGER
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true)
                )
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    //  AUTHENTICATION PROVIDER THAT USES MY USER_DETAIL_SERVER
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());
        return daoAuthenticationProvider;
    }

    //    SESSION MANAGER
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }


}

//    @Bean
//    public AuthenticationProvider authenticationProvider(AuthenticationManagerBuilder auth){
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//
//    }


//    @Bean
//    public AuthenticationManager authenticationManager(){
//            UserDetailsService userDetailsService,
//            PasswordEncoder passwordEncoder) {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//
//        return new ProviderManager(authenticationProvider);
//    }








//WORKING IN MEMORY LOGIN FOR THE DEFAULT LOGIN FORM

//    @Bean
//    public UserDetailsService users() {
//        return userDetailsService;
////        UserDetails user = User.builder()
////                .username("user")
////                .password("$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
////                .roles("USER")
////                .build();
////        UserDetails admin = User.builder()
////                .username("admin")
////                .password("$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
////                .roles("USER", "ADMIN")
////                .build();
////        return new InMemoryUserDetailsManager(user, admin);
//    }
//}
