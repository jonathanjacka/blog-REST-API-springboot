package com.springboot.blog.controller;

import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(
        name = "CRUD routes for Category Resource - REST API"
)
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //Get all categories REST API
    @Operation(
            summary = "Get All Categories by Id REST API",
            description = "Returns a list of categories based on Id. Throws an error if category with Id is not found."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        List<CategoryDto> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    //Get category by ID REST API
    @Operation(
            summary = "Get Category by Id REST API",
            description = "Updates single comment by Id. Requires that all fields are sent in request body. Throws an error if either post with Id, or comment with Id is not found."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable(value = "id") long id){
        CategoryDto result = categoryService.getCategory(id);
        return ResponseEntity.ok(result);
    }

    //Add category REST API
    @Operation(
            summary = "Create Comment REST API",
            description = "Creates a new category. Requires that all fields are sent in request body."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Bearer Authorization"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto newCategory = categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    //Update category REST API
    @Operation(
            summary = "Update Category by Id REST API",
            description = "Updates single category by Id. Requires that all fields are sent in request body. Throws an error if category with Id is not found."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authorization"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(
            @RequestBody CategoryDto categoryDto,
            @PathVariable(value = "id") long id) {

        CategoryDto updatedCategory = categoryService.updateCategory(categoryDto, id);

        return ResponseEntity.ok(updatedCategory);
    }

    //Delete category REST API
    @Operation(
            summary = "Delete Category by Id REST API",
            description = "Removes single category from database by Id. Throws an error if category with Id is not found."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authorization"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDto> deleteCategory(@PathVariable(value = "id") long id){
        CategoryDto deletedCategory = categoryService.deleteCategory(id);

        return ResponseEntity.ok(deletedCategory);
    }
}
