package org.example.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dtos.user.ForgotPasswordDTO;
import org.example.dtos.user.ResetPasswordDTO;
import org.example.dtos.user.UsersRegisterDTO;
import org.example.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/forgot-password")
    public String forgotPasswordRequestForm(Model model){
        model.addAttribute("forgotPassword", new ForgotPasswordDTO());
        return "users/forgotPassword";
    }

    @PostMapping("/forgot-password")
    public String resetPassword(ForgotPasswordDTO dto, HttpServletRequest request){
        userService.forgotPassword(dto, request);
        return "users/forgotPasswordCheckEmail";
    }


    @GetMapping("/reset-password")
    public String resetPasswordForm(@RequestParam("token") String token, Model model) {
        ResetPasswordDTO dto = new ResetPasswordDTO();
        dto.setToken(token);
        model.addAttribute("resetPasswordDTO", dto);
        return "users/resetPassword";
    }

    @PostMapping("/reset-password")
    public String resetPasswordSubmit(
            @Valid @ModelAttribute ResetPasswordDTO resetPasswordDTO,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "users/resetPassword";
        }

        boolean success = userService.resetPassword(resetPasswordDTO);
        if (success) {
            model.addAttribute("message", "Пароль успішно змінено! Тепер ви можете увійти.");
            return "users/login";
        } else {
            model.addAttribute("error", "Токен недійсний або паролі не співпадають.");
            return "users/resetPassword";
        }
    }

}
