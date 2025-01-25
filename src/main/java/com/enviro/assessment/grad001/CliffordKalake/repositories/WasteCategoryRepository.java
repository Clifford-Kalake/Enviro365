package com.enviro.assessment.grad001.CliffordKalake.repositories;

import com.enviro.assessment.grad001.CliffordKalake.model.WasteCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WasteCategoryRepository extends JpaRepository<WasteCategory, Long> {
    /**
     * Checks if a WasteCategory exists by its name, ignoring case.
     *
     * @param name the name of the WasteCategory
     * @return true if a WasteCategory with the given name exists, false otherwise
     */
    boolean existsByNameIgnoreCase(String name);
}