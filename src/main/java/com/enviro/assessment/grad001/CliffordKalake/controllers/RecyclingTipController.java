package com.enviro.assessment.grad001.CliffordKalake.controllers;

import com.enviro.assessment.grad001.CliffordKalake.dtos.RecyclingTipDTO;
import com.enviro.assessment.grad001.CliffordKalake.services.RecyclingTipService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@Validated
public class RecyclingTipController {

    /**
     * Service for managing recycling tips.
     */
    private final RecyclingTipService recyclingTipService;

    /**
     * Constructor for RecyclingTipController.
     *
     * @param recyclingTipService the service used to manage recycling tips
     */
    public RecyclingTipController(RecyclingTipService recyclingTipService) {
        this.recyclingTipService = recyclingTipService;
    }

    /**
     * Retrieves all recycling tips.
     *
     * @return a ResponseEntity containing a list of RecyclingTipDTO objects
     */
    @GetMapping("/api/recycling-tips")
    public ResponseEntity<List<RecyclingTipDTO>> getAllTips() {
        return ResponseEntity.ok(recyclingTipService.getAllTips());
    }

    /**
     * Creates a new recycling tip.
     *
     * @param tipDTO the RecyclingTipDTO object containing the details of the recycling tip to be created
     * @return a ResponseEntity containing the created RecyclingTipDTO object and HTTP status CREATED
     */
    @PostMapping("/api/recycling-tips")
    public ResponseEntity<RecyclingTipDTO> createTip(@Valid @RequestBody RecyclingTipDTO tipDTO) {
        return new ResponseEntity<>(recyclingTipService.createTip(tipDTO), HttpStatus.CREATED);
    }

    /**
     * Deletes a recycling tip by its ID.
     *
     * @param id the ID of the recycling tip to be deleted
     * @return a ResponseEntity with HTTP status NO_CONTENT
     */
    @DeleteMapping("/api/recycling-tips/{id}")
    public ResponseEntity<Void> deleteTip(@PathVariable Long id) {
        recyclingTipService.deleteTip(id);
        return ResponseEntity.noContent().build();
    }
}