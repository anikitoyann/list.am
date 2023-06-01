package com.example.listam.service.impl;

import com.example.listam.entity.Category;
import com.example.listam.repository.CategoryRepository;
import com.example.listam.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;


    @Override
    public List<Category> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);

    }

    @Override
    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }

}



