package com.enviro.assessment.grad001.CliffordKalake.services;

import com.enviro.assessment.grad001.CliffordKalake.dtos.DisposalGuidelineDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DisposalGuidelineService {
    List<DisposalGuidelineDTO> getGuidelinesByCategory(Long categoryId);
    DisposalGuidelineDTO createGuideline(DisposalGuidelineDTO guidelineDTO);
    DisposalGuidelineDTO updateGuideline(Long id, DisposalGuidelineDTO guidelineDTO);
    void deleteGuideline(Long id);
}