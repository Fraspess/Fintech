package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dtos.user.UsersRegisterDTO;
import org.example.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;

    @GetMapping("/registration")
    public String showRegistrationForm(Model model){
        var userDTO = new UsersRegisterDTO();
        model.addAttribute("user", userDTO);
        return "users/registration";
    }

    @PostMapping("/registration")
    public String save(UsersRegisterDTO userDTO){
        userService.createUser(userDTO);
        return "redirect:/users/login";
    }


    @GetMapping("/login")
    public String showLoginForm(){
        return "users/login";
    }

    @GetMapping("/reset-password")
    public String resetPasswordRequestForm(){
        return "users/resetPasswordRequest";
    }

    @PostMapping("/reset-password")
    public String resetPasswordEmail(String email){
        return "";
    }

    public String updatePasswordRequest(){
        return "";
    }

}
