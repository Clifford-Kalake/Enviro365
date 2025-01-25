package com.enviro.assessment.grad001.CliffordKalake.repositories;

import com.enviro.assessment.grad001.CliffordKalake.model.DisposalGuideline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisposalGuidelineRepository extends JpaRepository<DisposalGuideline, Long> {
    /**
     * Finds a list of DisposalGuideline entities by the given category ID.
     *
     * @param categoryId the ID of the category to filter by
     * @return a list of DisposalGuideline entities that belong to the specified category
     */
    List<DisposalGuideline> findByCategoryId(Long categoryId);
}