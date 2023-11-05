package com.project.Testing.config;

import com.project.Testing.model.User;
import com.project.Testing.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.ArrayList;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //Creating Custom Authentication Provider Details. So we can use login function with custom login and password.
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

            User user = userRepo.findByEmail(email);

        //print error message which prints under the form
            if(user == null){
                throw new BadCredentialsException("User does not exist!");
            }
//print error message which prints under the form
        if (passwordEncoder.matches(password, user.getPassword())){
            return new UsernamePasswordAuthenticationToken(email, password, new ArrayList<>());
        }
        else {
            throw new BadCredentialsException("Invalid Credentials!");
        }
    }

    //Create authentication token (Just a required step).
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
