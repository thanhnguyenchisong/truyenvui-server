package com.sky.tv.comics.service;

import com.sky.tv.comics.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public void getAllCategory() {
        categoryRepository.findAll();
    }
}
