package com.project.Testing.controller;

import com.project.Testing.model.User;
import com.project.Testing.repository.UserRepo;
import com.project.Testing.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class UserController {

    //Importing UserService
    @Autowired
    private UserService userService;

    //Importing UserRepo. (Repository)
    @Autowired
    private UserRepo userRepo;

    //Register visit page
    @GetMapping("/register")
    public String showRegistrationForm(org.springframework.ui.Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    //Register after clicking save
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model, HttpSession session) throws Exception {

        //print error message which prints under the form
        try{
            if (bindingResult.hasErrors()) {
                model.addAttribute("errorMessage", "Please read the password instructions.");
                return "register";
            }
            if (userService.registerUser(user) != null) {
                model.addAttribute("successMessage", "Registration successful!");
                return "register"; // Redirect to a success page
            } else {
                model.addAttribute("errorMessage", "Maybe email already exist!");
                return "register";
            }
        } catch (Exception e){
            model.addAttribute("errorMessage", e.fillInStackTrace());
            return "register";
        }

    }

    //Visit login page
//    @GetMapping("/login")
//    public String login(){
//        return "login";
//    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/profile")
    public String profile(Principal p, Model model){
        String email = p.getName();
        User user = userRepo.findByEmail(email);
        model.addAttribute("user",user);
        return "profile";
    }

    //Print message
    public void commonUser(Principal p, Model m){
        if(p!=null){
            String email = p.getName();
            User user = userRepo.findByEmail(email);
            m.addAttribute("user",user);
        } else {
            m.addAttribute("user","null");
        }
    }


    //Logout
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login?logout";
    }

    //After logout visit login page again.
    @PostMapping("/logout")
    public String logout(org.springframework.ui.Model model) {
        model.addAttribute("user", null);
        return "signin";
    }
}
