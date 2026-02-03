package org.example.mappers;

import com.github.slugify.Slugify;
import org.example.dtos.category.CategoryItemDTO;
import org.example.dtos.category.CreateCategoryDTO;
import org.example.entities.CategoryEntity;
import org.example.services.FileService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {
    @Autowired
    protected FileService fileService;

    protected Slugify slg = Slugify.builder().transliterator(true).build();
    public abstract CategoryItemDTO toDto(CategoryEntity category);

    public CategoryEntity fromCreateDto(CreateCategoryDTO dto){
        if(dto == null){
            return null;
        }
        CategoryEntity category = new CategoryEntity();
        category.setName(dto.getName());
        if(dto.getImage() != null){
            String imageName = fileService.load(dto.getImage());
            category.setImage(imageName);
        }
        if(dto.getSlug() == null){
            category.setSlug(slg.slugify(dto.getName()));
        }
        else{
            category.setSlug(dto.getSlug());
        }
        return category;
    }
}
