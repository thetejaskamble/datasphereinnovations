package com.project.Testing.config;

import com.project.Testing.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService getDetailsService(){
        return new CustomUserDetailsService();
    }
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(getDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .authorizeHttpRequests().requestMatchers("/","/about","register","/login","/userLogin").permitAll()
                .requestMatchers("/**").authenticated().and()
                .formLogin().loginPage("/login").loginProcessingUrl("/userLogin")
                .usernameParameter("email")
                .defaultSuccessUrl("/companies").permitAll().and().logout().logoutUrl("/logout").logoutSuccessUrl("/login").permitAll();;
        return httpSecurity.build();
    }
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth, CustomAuthenticationProvider customAuthenticationProvider) {
        auth.authenticationProvider(customAuthenticationProvider);
    }
}
