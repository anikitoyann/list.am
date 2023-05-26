package com.example.listam.controller;

import com.example.listam.entity.Category;
import com.example.listam.repository.CategoryRepository;
import com.example.listam.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor

@Controller
@RequestMapping("/categories")
public class CategoryController {

  private final CategoryService categoryService;

    @GetMapping()
    public String categoriesPage(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryService.findAll());
        return "categories";
    }

    @GetMapping("/add")
    public String addCategoryPage() {
        return "addCategory";
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute Category category) {
       categoryService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/remove")
    public String removeCategory(@RequestParam("id") int id) {
        categoryService.deleteById(id);
        return "redirect:/categories";
    }
}
