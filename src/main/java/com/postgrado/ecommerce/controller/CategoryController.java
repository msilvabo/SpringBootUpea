package com.postgrado.ecommerce.controller;

import com.postgrado.ecommerce.entity.Category;
import com.postgrado.ecommerce.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.postgrado.ecommerce.exception.response.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Category")
@AllArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    @Operation(
            summary = "Get Category by ID",
            responses =
                    {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Category Found",
                                    content = @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = Category.class)
                                    )
                            ),
                            @ApiResponse(
                                    responseCode = "404",
                                    description = "Category not Found",
                                    content = @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ErrorResponse.class)
                                    )
                            )
                    }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(
            @Parameter(description = "Category Id for search")
            @PathVariable UUID id
    ) {
        Category categoryFound = categoryService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryFound);
    }

    @Operation(summary = "Get All Categories")
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @Operation(
            summary = "Create new Category",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Category Created",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Category.class)
                            )
                    )
            }
    )
    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category) {
        Category categoryCreated = categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryCreated);
    }

    @Operation(
            summary = "Update Category by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Category Updated",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Category.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Category not Found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    )
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Category> update(
            @Parameter(description = "Category Id for update")
            @PathVariable UUID id,
            @RequestBody Category category
    ) {
        Category categoryUpdated = categoryService.updateCategory(id, category);
        return ResponseEntity.status(HttpStatus.OK).body(categoryUpdated);
    }

    @Operation(
            summary = "Delete Category by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Category Deleted"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Category not Found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    )
            }
    )

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Category Id for delete")
            @PathVariable UUID id
    ) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}
