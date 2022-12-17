package com.example.carrental.springsecurity.controller;

import com.example.carrental.controller.CarController;
import com.example.carrental.service.MailService;
import com.example.carrental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class AuthController {

    private final UserService userService;
    private final MailService mailService;
    private static final Logger LOGGER = Logger.getLogger(CarController.class.getName());

    @Autowired
    public AuthController(UserService userService, MailService mailService) {
        this.userService = userService;
        this.mailService = mailService;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/success")
    public String getSuccessPage() {
        return "success";
    }

    @GetMapping("/registration")
    public String getRegPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam String email,
                          @RequestParam String pass,
                          @RequestParam String confirmPass, Model model) {
        try {
            String regex = "^(|(([A-Za-z0-9]+_+)|([A-Za-z0-9]+\\-+)|([A-Za-z0-9]+\\.+)|([A-Za-z0-9]+\\++))*[A-Za-z0-9]+@((\\w+\\-+)|(\\w+\\.))*\\w{1,63}\\.[a-zA-Z]{2,6})$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            boolean isGoodPassword = pass.equals(confirmPass);
            if(matcher.matches() && isGoodPassword) {
                userService.add(email, new BCryptPasswordEncoder(12).encode(pass));
                model.addAttribute("isAddedUser", true);
                mailService.send(email, "Activation", "Для входа в систему необходимо ввести данные\n" +
                        "Email: " + email + "\n" +
                        "Password: " + pass);
            } else {
                model.addAttribute("isNotAddedUser", true);
            }
            return "registration";
        } catch (Exception ignore) {
            ignore.printStackTrace();
            LOGGER.log(Level.INFO, ignore.getMessage());
            return "error";
        }
    }

}
