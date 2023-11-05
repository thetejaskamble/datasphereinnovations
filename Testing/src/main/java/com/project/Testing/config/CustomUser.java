// This is the package name for the configuration classes
package com.project.Testing.config;

// Import the User model class
import com.project.Testing.model.User;
// Import the Spring Security interfaces for user details and authorities
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// Import the Java collections framework
import java.util.Arrays;
import java.util.Collection;

// Define a custom user class that implements the UserDetails interface
public class CustomUser implements UserDetails {
    // Declare a private field to store the user object
    private User user;
    // Define a constructor that takes a user object as a parameter and assigns it to the field
    public CustomUser(User user) {
        this.user = user;
    }
    // Override the getAuthorities method to return a collection of granted authorities for the user
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Create a simple granted authority object with the user's role as the authority name
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(user.getRole());
        // Return a list containing the simple granted authority object
        return Arrays.asList(simpleGrantedAuthority);
    }
    // Override the getPassword method to return the user's password
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    // Override the getUsername method to return the user's email as the username
    @Override
    public String getUsername() {
        return user.getEmail();
    }
    // Override the isAccountNonExpired method to return true, indicating that the account is not expired
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    // Override the isAccountNonLocked method to return true, indicating that the account is not locked
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    // Override the isCredentialsNonExpired method to return true, indicating that the credentials are not expired
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    // Override the isEnabled method to return true, indicating that the account is enabled
    @Override
    public boolean isEnabled() {
        return true;
    }
}
