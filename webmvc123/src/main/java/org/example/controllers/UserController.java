package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dtos.user.LoginUserDTO;
import org.example.dtos.user.UserDTO;
import org.example.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/registration")
    public String showRegistrationForm(Model model){
        var userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "registration";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model){
        return "login";
    }

    @PostMapping("/save")
    public String save(UserDTO userDTO){
        userService.createUser(userDTO);
        return "redirect:/login";
    }


//    @GetMapping("/login")
//    public String showLoginForm(Model model){
//
//    }
}
