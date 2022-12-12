package com.funpaycopy.oes.Controller;

import com.funpaycopy.oes.Model.Role;
import com.funpaycopy.oes.Model.User;
import com.funpaycopy.oes.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Collections;

@Controller
public class AuthController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/reg")
    public String registrationView(User user_, Model model) {

        model.addAttribute("user_", user_);
        return ("registration");
    }

    @PostMapping("/reg")
    public String registration(@Valid User user_, BindingResult result, Model model) {

        if (result.hasErrors()) {

            model.addAttribute("exception", "Ошибка при регистрации!");
            model.addAttribute("user_", user_);
            return ("registration");
        }

        try {
            if (userRepository.findByLogin(user_.getLogin()) == null) {

                if(userRepository.findByEmail(user_.getEmail()) == null) {

                    User user = new User();

                    user.setLogin(user_.getLogin());
                    user.setPassword(passwordEncoder.encode(user_.getPassword()));
                    user.setEmail(user_.getEmail());

                    user.setRoles(Collections.singleton(Role.USER));
                    user.setProfilePhoto("/usericons/def.jpg");

                    user.setBalance(BigDecimal.valueOf(0.00));
                    user.setActive(true);

                    userRepository.save(user);
                } else {
                    throw new Exception("Почта уже используется!");
                }
            } else {
                throw new Exception("Пользователь уже есть в системе!");
            }

        } catch (Exception exception) {
            if (exception.getMessage().length() < 50) {
                model.addAttribute("exception", exception.getMessage());
            } else {
                model.addAttribute("exception", "Что-то пошло не так :)");
            }

            return ("registration");
        }

        model.addAttribute("login", user_.getLogin());
        return ("redirect:/login");
    }
}
