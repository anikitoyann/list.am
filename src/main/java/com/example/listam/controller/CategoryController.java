package com.example.listam.controller;

import com.example.listam.entity.Category;
import com.example.listam.repository.CategoryRepository;
import com.example.listam.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor

@Controller
@RequestMapping("/categories")
public class CategoryController {

  private final CategoryService categoryService;
  private final CategoryRepository categoryRepository;

    @GetMapping()
    public String categoriesPage(
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            ModelMap modelMap) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Sort sort = Sort.by(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize, sort);

        Page<Category> result = categoryRepository.findAll(pageable);
        int totalPages = result.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }
        modelMap.addAttribute("categories", result);
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
