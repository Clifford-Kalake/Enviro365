package com.enviro.assessment.grad001.CliffordKalake.services;

import com.enviro.assessment.grad001.CliffordKalake.dtos.WasteCategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface WasteCategoryService {
    List<WasteCategoryDTO> getAllCategories();
    WasteCategoryDTO getCategory(Long id);
    WasteCategoryDTO createCategory(WasteCategoryDTO categoryDTO);
    WasteCategoryDTO updateCategory(Long id, WasteCategoryDTO categoryDTO);
    void deleteCategory(Long id);
}