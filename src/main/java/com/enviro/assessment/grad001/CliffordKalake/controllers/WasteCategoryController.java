package com.enviro.assessment.grad001.CliffordKalake.controllers;

import com.enviro.assessment.grad001.CliffordKalake.dtos.WasteCategoryDTO;
import com.enviro.assessment.grad001.CliffordKalake.services.WasteCategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@Validated
public class WasteCategoryController {

    /**
     * Service for handling waste category operations.
     */
    private final WasteCategoryService wasteCategoryService;

    /**
     * Constructs a new WasteCategoryController with the specified WasteCategoryService.
     *
     * @param wasteCategoryService the service to handle waste category operations
     */
    public WasteCategoryController(WasteCategoryService wasteCategoryService) {
        this.wasteCategoryService = wasteCategoryService;
    }

    /**
     * Retrieves all waste categories.
     *
     * @return a ResponseEntity containing a list of WasteCategoryDTO objects
     */
    @GetMapping("/api/waste-categories")
    public ResponseEntity<List<WasteCategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(wasteCategoryService.getAllCategories());
    }

    /**
     * Retrieves a waste category by its ID.
     *
     * @param id the ID of the waste category to retrieve
     * @return a ResponseEntity containing the WasteCategoryDTO object
     */
    @GetMapping("/api/waste-categories/{id}")
    public ResponseEntity<WasteCategoryDTO> getCategory(@PathVariable Long id) {
        return ResponseEntity.ok(wasteCategoryService.getCategory(id));
    }

    /**
     * Creates a new waste category.
     *
     * @param categoryDTO the DTO object containing the details of the waste category to create
     * @return a ResponseEntity containing the created WasteCategoryDTO object and HTTP status CREATED
     */
    @PostMapping("/api/waste-categories")
    public ResponseEntity<WasteCategoryDTO> createCategory(@Valid @RequestBody WasteCategoryDTO categoryDTO) {
        return new ResponseEntity<>(wasteCategoryService.createCategory(categoryDTO), HttpStatus.CREATED);
    }

    /**
     * Updates an existing waste category.
     *
     * @param id the ID of the waste category to update
     * @param categoryDTO the DTO object containing the updated details of the waste category
     * @return a ResponseEntity containing the updated WasteCategoryDTO object
     */
    @PutMapping("/api/waste-categories/{id}")
    public ResponseEntity<WasteCategoryDTO> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody WasteCategoryDTO categoryDTO) {
        return ResponseEntity.ok(wasteCategoryService.updateCategory(id, categoryDTO));
    }

    /**
     * Deletes a waste category by its ID.
     *
     * @param id the ID of the waste category to delete
     * @return a ResponseEntity with HTTP status NO_CONTENT
     */
    @DeleteMapping("/api/waste-categories/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        wasteCategoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}