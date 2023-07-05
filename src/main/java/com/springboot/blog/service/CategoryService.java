package com.springboot.blog.service;


import com.springboot.blog.payload.CategoryDto;

public interface CategoryService {

    CategoryDto getCategory (long categoryId);
    CategoryDto addCategory(CategoryDto categoryDto);
}
