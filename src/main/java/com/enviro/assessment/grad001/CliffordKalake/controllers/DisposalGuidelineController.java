package com.enviro.assessment.grad001.CliffordKalake.controllers;

import com.enviro.assessment.grad001.CliffordKalake.dtos.DisposalGuidelineDTO;
import com.enviro.assessment.grad001.CliffordKalake.services.DisposalGuidelineService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@Validated
public class DisposalGuidelineController {

    /**
     * Service for handling disposal guideline operations.
     */
    private final DisposalGuidelineService disposalGuidelineService;

    /**
     * Constructs a new DisposalGuidelineController with the specified DisposalGuidelineService.
     *
     * @param disposalGuidelineService the service to handle disposal guideline operations
     */
    public DisposalGuidelineController(DisposalGuidelineService disposalGuidelineService) {
        this.disposalGuidelineService = disposalGuidelineService;
    }

    /**
     * Retrieves a list of disposal guidelines by category.
     *
     * @param categoryId the ID of the category to retrieve guidelines for
     * @return a ResponseEntity containing a list of DisposalGuidelineDTOs
     */
    @GetMapping("/api/disposal-guidelines/{categoryId}")
    public ResponseEntity<List<DisposalGuidelineDTO>> getGuidelinesByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(disposalGuidelineService.getGuidelinesByCategory(categoryId));
    }

    /**
     * Creates a new disposal guideline.
     *
     * @param guidelineDTO the DTO containing the details of the guideline to create
     * @return a ResponseEntity containing the created DisposalGuidelineDTO and HTTP status CREATED
     */
    @PostMapping("/api/disposal-guidelines")
    public ResponseEntity<DisposalGuidelineDTO> createGuideline(@Valid @RequestBody DisposalGuidelineDTO guidelineDTO) {
        return new ResponseEntity<>(disposalGuidelineService.createGuideline(guidelineDTO), HttpStatus.CREATED);
    }

    /**
     * Updates an existing disposal guideline.
     *
     * @param id the ID of the guideline to update
     * @param guidelineDTO the DTO containing the updated details of the guideline
     * @return a ResponseEntity containing the updated DisposalGuidelineDTO
     */
    @PutMapping("/api/disposal-guidelines/{id}")
    public ResponseEntity<DisposalGuidelineDTO> updateGuideline(
            @PathVariable Long id,
            @Valid @RequestBody DisposalGuidelineDTO guidelineDTO) {
        return ResponseEntity.ok(disposalGuidelineService.updateGuideline(id, guidelineDTO));
    }

    /**
     * Deletes an existing disposal guideline.
     *
     * @param id the ID of the guideline to delete
     * @return a ResponseEntity with HTTP status NO_CONTENT
     */
    @DeleteMapping("/api/disposal-guidelines/{id}")
    public ResponseEntity<Void> deleteGuideline(@PathVariable Long id) {
        disposalGuidelineService.deleteGuideline(id);
        return ResponseEntity.noContent().build();
    }
}