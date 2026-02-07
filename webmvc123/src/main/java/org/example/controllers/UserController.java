package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dtos.user.LoginUserDTO;
import org.example.dtos.user.UserDTO;
import org.example.entities.UserEntity;
import org.example.repositories.IUserRepository;
import org.example.services.UserService;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController implements UserDetailsService {
    private final UserService userService;
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String showRegistrationForm(Model model){
        var userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "registration";
    }

    @PostMapping("/save")
    public String save(UserDTO userDTO){
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userService.createUser(userDTO);
        return "redirect:/user/login";
    }


    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>()
        );
    }
}
