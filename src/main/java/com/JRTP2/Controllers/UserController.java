package com.JRTP2.Controllers;

import com.JRTP2.BindingClasses.LoginForm;
import com.JRTP2.BindingClasses.SignupForm;
import com.JRTP2.Entity.UserEntity;
import com.JRTP2.Repositories.UserRepo;
import com.JRTP2.Services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private HttpSession session;

    @GetMapping("/signUp")
    public String signup(Model model) {
        model.addAttribute("user", new SignupForm());
        return "signup_page";
    }

    @PostMapping("/signUp")
    public String signup(@ModelAttribute("user") SignupForm form, Model model) {
        boolean status = userService.signUp(form);
        if(status){
            model.addAttribute("succMsg","Signed up successfully");
        }else{
            model.addAttribute("errMsg","Email Id must be unique");
        }
        return "signup_page";
    }

    @GetMapping("/signIn")
    public String login(Model model){
        model.addAttribute("loginForm", new LoginForm());
        return "login_page";
    }

    @PostMapping("/signIn")
    public String login(@ModelAttribute("loginForm") LoginForm loginForm, Model model){
        boolean status = userService.login(loginForm);

        if (!status) {
            model.addAttribute("errMsg", "Invalid Email or Password");
            return "login_page";
        }

        // fetch user only to store session
        UserEntity user =
                userRepo.findByEmail(loginForm.getEmail());

        session.setAttribute("userId", user.getUserId());

        // 🔥 THIS LINE IS THE MOST IMPORTANT
        return "redirect:/posts";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/signIn";
    }

}
