package com.springboot.blog.service;


import com.springboot.blog.payload.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAllCategories ();
    CategoryDto getCategory (long categoryId);
    CategoryDto addCategory(CategoryDto categoryDto);
}
