package com.example.demo_sevirity.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SercurityConfiguration {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery("select user_id, password, is_active from members where user_id = ?");
        userDetailsManager.setAuthoritiesByUsernameQuery("select user_id,role from roles where user_id = ?");
        return userDetailsManager;
    }
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(authorize -> authorize
                    .requestMatchers("/dashboard/**").hasAnyRole("ADMIN", "OPERATOR")
                    .requestMatchers("/home/**").hasAnyRole("USER")

                    .anyRequest().permitAll()
            )
            .formLogin(formLogin -> formLogin
                    .loginPage("/loginPage")
                    .loginProcessingUrl("/authenticateUser")
                    .permitAll()
            )
            .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/loginPage")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
            );
    return http.build();
}
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{noop}123")
//                .roles("USER").build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{noop}123")
//                .roles("ADMIN").build();
//        UserDetails operator = User.builder()
//                .username("operator")
//                .password("{noop}123")
//                .roles("OPERATOR").build();
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(user);
//        manager.createUser(admin);
//        manager.createUser(operator);
//        return manager;
//    }
}
