package org.example.mappers;

import org.apache.catalina.User;
import org.example.dtos.category.CreateCategoryDTO;
import org.example.dtos.user.UserDTO;
import org.example.entities.CategoryEntity;
import org.example.entities.UserEntity;
import org.example.services.FileService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Autowired
    private FileService fileService;

    public UserEntity fromCreateDto(UserDTO dto) {
        if (dto == null)return null;
        UserEntity user = new UserEntity();
        if(dto.getUsername() == null) return null;
        if(dto.getEmail() == null) return null;
        if(dto.getPassword() == null) return null;

        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        if (dto.getImage() != null){
            String fileName = fileService.load(dto.getImage());
            user.setImage(fileName);
        }
        return user;
    }
}
