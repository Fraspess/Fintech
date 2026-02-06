package org.example.dtos.user;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class LoginUserDTO {
    @NotNull
    private String username;

    @NotNull
    private String password;;
}
