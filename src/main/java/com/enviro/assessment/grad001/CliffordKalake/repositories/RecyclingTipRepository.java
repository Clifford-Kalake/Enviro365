package com.enviro.assessment.grad001.CliffordKalake.repositories;

import com.enviro.assessment.grad001.CliffordKalake.model.RecyclingTip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecyclingTipRepository extends JpaRepository<RecyclingTip, Long> {
    /**
     * Checks if a RecyclingTip exists with the given title, ignoring case.
     *
     * @param title the title of the RecyclingTip
     * @return true if a RecyclingTip exists with the given title, ignoring case, false otherwise
     */
    boolean existsByTitleIgnoreCase(String title);
}