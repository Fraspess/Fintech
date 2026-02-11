package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.dtos.user.UsersRegisterDTO;
import org.example.entities.UserEntity;
import org.example.mappers.UserMapper;
import org.example.repositories.IUserRepository;
import org.example.smtp.EmailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final IUserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final SmtpService smtpService;

    public void createUser(UsersRegisterDTO userDTO){
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        UserEntity user = userMapper.fromCreateDto(userDTO);
        userRepository.save(user);
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

    public void sendResetPasswordToken(String email){
        if (email != null){
            EmailMessage message = new EmailMessage();
        }
    }

}
