package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.dtos.user.UserDTO;
import org.example.entities.UserEntity;
import org.example.mappers.UserMapper;
import org.example.repositories.IUserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepository userRepository;
    private final UserMapper userMapper;

    public void createUser(UserDTO userDTO){
        UserEntity user = userMapper.fromCreateDto(userDTO);
        userRepository.save(user);
    }
}
