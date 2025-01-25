package com.enviro.assessment.grad001.CliffordKalake.services.impl;

import com.enviro.assessment.grad001.CliffordKalake.dtos.WasteCategoryDTO;
import com.enviro.assessment.grad001.CliffordKalake.exception.ResourceNotFoundException;
import com.enviro.assessment.grad001.CliffordKalake.model.WasteCategory;
import com.enviro.assessment.grad001.CliffordKalake.repositories.DisposalGuidelineRepository;
import com.enviro.assessment.grad001.CliffordKalake.repositories.WasteCategoryRepository;
import com.enviro.assessment.grad001.CliffordKalake.services.WasteCategoryService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class WasteCategoryServiceImpl implements WasteCategoryService {

    private final WasteCategoryRepository categoryRepository;
    private final DisposalGuidelineRepository guidelineRepository;
    private final ModelMapper modelMapper;

    /**
     * Constructs a new WasteCategoryServiceImpl with the specified repositories and model mapper.
     *
     * @param categoryRepository the repository for waste categories
     * @param guidelineRepository the repository for disposal guidelines
     * @param modelMapper the model mapper for converting entities to DTOs
     */
    @Autowired
    public WasteCategoryServiceImpl(
            WasteCategoryRepository categoryRepository,
            DisposalGuidelineRepository guidelineRepository,
            ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.guidelineRepository = guidelineRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Retrieves all waste categories.
     *
     * @return a list of WasteCategoryDTO objects representing all waste categories
     */
    @Override
    @Transactional(readOnly = true)
    public List<WasteCategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> modelMapper.map(category, WasteCategoryDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a waste category by its ID.
     *
     * @param id the ID of the waste category to retrieve
     * @return a WasteCategoryDTO object representing the waste category
     * @throws ResourceNotFoundException if no waste category is found with the specified ID
     */
    @Override
    @Transactional(readOnly = true)
    public WasteCategoryDTO getCategory(Long id) {
        WasteCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Waste category not found with id: " + id));
        return modelMapper.map(category, WasteCategoryDTO.class);
    }

    /**
     * Creates a new waste category.
     *
     * @param categoryDTO the DTO object containing the details of the waste category to create
     * @return a WasteCategoryDTO object representing the created waste category
     * @throws IllegalArgumentException if a category with the same name already exists
     */
    @Override
    public WasteCategoryDTO createCategory(WasteCategoryDTO categoryDTO) {
        if (categoryRepository.existsByNameIgnoreCase(categoryDTO.getName())) {
            throw new IllegalArgumentException("A category with this name already exists");
        }

        WasteCategory category = modelMapper.map(categoryDTO, WasteCategory.class);
        category = categoryRepository.save(category);
        return modelMapper.map(category, WasteCategoryDTO.class);
    }

    /**
     * Updates an existing waste category.
     *
     * @param id the ID of the waste category to update
     * @param categoryDTO the DTO object containing the updated details of the waste category
     * @return a WasteCategoryDTO object representing the updated waste category
     * @throws ResourceNotFoundException if no waste category is found with the specified ID
     * @throws IllegalArgumentException if a category with the same name already exists
     */
    @Override
    public WasteCategoryDTO updateCategory(Long id, WasteCategoryDTO categoryDTO) {
        WasteCategory existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Waste category not found with id: " + id));

        if (!existingCategory.getName().equalsIgnoreCase(categoryDTO.getName()) &&
                categoryRepository.existsByNameIgnoreCase(categoryDTO.getName())) {
            throw new IllegalArgumentException("A category with this name already exists");
        }

        modelMapper.map(categoryDTO, existingCategory);
        existingCategory = categoryRepository.save(existingCategory);
        return modelMapper.map(existingCategory, WasteCategoryDTO.class);
    }

    /**
     * Deletes a waste category by its ID.
     *
     * @param id the ID of the waste category to delete
     * @throws ResourceNotFoundException if no waste category is found with the specified ID
     */
    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Waste category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }
}