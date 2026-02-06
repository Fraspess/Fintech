package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dtos.category.CreateCategoryDTO;
import org.example.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/")
    public String hello(Model model){
        var items = categoryService.getAll();
        model.addAttribute("categories", items);
        return "salo-hello";
    }
    @GetMapping("add")
    public String addCategory(Model model){
        CreateCategoryDTO dto = new CreateCategoryDTO();
        model.addAttribute("CategoryEntity", dto);
        return "add-salo";
    }

    @PostMapping("save")
    public String save(CreateCategoryDTO dto){
        categoryService.createCategory(dto);
        return "redirect:/categories/";
    }
}
