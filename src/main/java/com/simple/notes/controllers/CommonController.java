package com.simple.notes.controllers;

import com.simple.notes.entities.Notes;
import com.simple.notes.entities.User;
import com.simple.notes.model.Login;
import com.simple.notes.repository.contract.NoteRepository;
import com.simple.notes.repository.contract.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

/**
 * Created by damodaram.setti on 9/23/2018.
 */
@Controller
public class CommonController {
    @Value("${spring.application.name}")
    String appName;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        model.addAttribute("appName", appName);
        model.addAttribute("user", new User());
        return "registration";
    }

    @GetMapping("/login")
    public String loginPage(Model model , @ModelAttribute("user") Object user) {
        if(user instanceof User){
            model.addAttribute("registrationStatus", "User registered successfully.");
        }
        model.addAttribute("login",new Login());
        return "login";
    }

    @PostMapping("/login")
    public String loginPage(Model model , @ModelAttribute("login") @Validated Login login,
                            BindingResult result,
                            final RedirectAttributes redirectAttributes,
                            HttpServletRequest request) {
        if (result.hasErrors()) {
            return "login";
        }
        if(userRepository.get(login.getEmailId(), login.getPassword())==null){
            model.addAttribute("errorMessage", "Invalid user/password");
            return "login";
        }
        HttpSession session = request.getSession(true);
        session.setAttribute("loginStatus","login success");
        return "redirect:/dashBoard";
    }

    @PostMapping("/registration")
    public String registrar(Model model,
                            @ModelAttribute("user") @Validated User user,
                            BindingResult result,
                            final RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "registration";
        }

        if(userRepository.get(user.getEmailId())!=null){
            model.addAttribute("errorMessage", "User Already exist in the system.");
            return "registration";
        }
        try {
            user.setId(UUID.randomUUID().toString());
            user = userRepository.save(user);
        }
        catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "registration";
        }
        redirectAttributes.addFlashAttribute("user", user);

        return "redirect:/login";
    }

}