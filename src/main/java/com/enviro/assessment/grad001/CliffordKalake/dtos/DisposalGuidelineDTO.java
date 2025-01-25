package com.enviro.assessment.grad001.CliffordKalake.dtos;

import com.enviro.assessment.grad001.CliffordKalake.model.WasteCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DisposalGuidelineDTO {

    private Long id;

    private String guideline;
    @NotBlank(message = "Instructions are required")
    @Size(max = 2000, message = "Instructions cannot exceed 2000 characters")
    private WasteCategory category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WasteCategory getCategory() {
        return category;
    }

    public void setCategory(WasteCategory category) {
        this.category = category;
    }

    public String getGuideline() {
        return guideline;
    }

    public void setGuideline(String guideline) {
        this.guideline = guideline;
    }
}