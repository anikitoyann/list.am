package com.example.listam.service;

import com.example.listam.entity.Category;
import com.example.listam.entity.User;

import java.util.List;

public interface CategoryService {
    List<Category>findAll();

    void save(Category category);

    void deleteById(int id);
}
