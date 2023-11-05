// This is the package name for the service implementation classes
package com.project.Testing.serviceImpl;

// Import the User model class
import com.project.Testing.model.User;
// Import the UserRepo interface that extends JpaRepository
import com.project.Testing.repository.UserRepo;
// Import the UserService interface that defines the service methods
import com.project.Testing.service.UserService;
// Import the Jakarta servlet classes for handling HTTP requests and sessions
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.message.Message;
// Import the Spring framework annotations and classes for dependency injection, encryption, and web context
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.thymeleaf.model.IModel;

// Annotate the class as a service class
@Service
// Define a service implementation class that implements the UserService interface
public class UserServiceImpl implements UserService {

    // Autowire the UserRepo object for interacting with the database
    @Autowired
    private UserRepo userRepository;

    // Define a constructor that takes a UserRepo object and a User object as parameters and assigns them to the fields
    public UserServiceImpl(UserRepo userRepository, User user) {
        this.userRepository = userRepository;
        this.user = user;
    }

    // Autowire the BCryptPasswordEncoder object for hashing passwords
    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    // Override the registerUser method to register a new user and save it to the database
    @Override
    public User registerUser(User user) throws Exception {
        // Additional validation and processing can be done here
        // Find an existing user by email
        User existingUser = userRepository.findByEmail(user.getEmail());

        // If the user already exists, return null
        if (existingUser != null) {
            return null;
        } else{
            // Otherwise, convert the email to lowercase and hash the password using the BCryptPasswordEncoder
            String email = user.getEmail();
            user.setEmail(email.toLowerCase());
            String password = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(password);
            // Save the user to the database and return it
            return userRepository.save(user);
        }

    }

    // Override the findByEmail method to find a user by email and return it
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Override the getString method to return a string message for email validation
    @Override
    public String getString() {
        return "Email already exists!";
    }

    // Declare a private field to store the user object
    private final User user;

    // Define a constructor that takes a User object as a parameter and assigns it to the field
    @Autowired
    public UserServiceImpl(User user) {
        this.user = user;
    }

    // Override the removeSessionMessage method to remove the message attribute from the session object
    @Override
    public void removeSessionMessage(){
        // Get the current HTTP session object from the request context holder
        HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
        // Remove the message attribute from the session object
        session.removeAttribute("msg");
    }

}
