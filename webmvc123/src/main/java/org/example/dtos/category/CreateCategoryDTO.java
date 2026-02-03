package org.example.dtos.category;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateCategoryDTO {
    private String name;
    private MultipartFile image;
    private String slug;
}
