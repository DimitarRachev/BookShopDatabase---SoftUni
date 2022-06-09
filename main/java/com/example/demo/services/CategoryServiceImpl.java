package com.example.demo.services;

import com.example.demo.entities.Category;
import com.example.demo.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> getRandomCategories() {
        Random random = new Random();
        long allCategorries = this.categoryRepository.count();
        int categoriesCount = random.nextInt((int) allCategorries) + 1;

        Set<Integer> categoriesId = new HashSet<>();

        for (int i = 0; i < categoriesCount; i++) {
            int nextId = random.nextInt((int) categoriesCount) + 1;
            categoriesId.add(nextId);
        }
        List<Category> allById = this.categoryRepository.findAllById(categoriesId);
        return new HashSet<>(allById);
    }
}
