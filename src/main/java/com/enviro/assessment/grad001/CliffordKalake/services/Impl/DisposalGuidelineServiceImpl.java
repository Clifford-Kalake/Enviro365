package com.enviro.assessment.grad001.CliffordKalake.services.Impl;

import com.enviro.assessment.grad001.CliffordKalake.dtos.DisposalGuidelineDTO;
import com.enviro.assessment.grad001.CliffordKalake.exception.ResourceNotFoundException;
import com.enviro.assessment.grad001.CliffordKalake.model.DisposalGuideline;
import com.enviro.assessment.grad001.CliffordKalake.model.WasteCategory;
import com.enviro.assessment.grad001.CliffordKalake.repositories.DisposalGuidelineRepository;
import com.enviro.assessment.grad001.CliffordKalake.repositories.WasteCategoryRepository;
import com.enviro.assessment.grad001.CliffordKalake.services.DisposalGuidelineService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DisposalGuidelineServiceImpl implements DisposalGuidelineService {

    private final DisposalGuidelineRepository guidelineRepository;
    private final WasteCategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    /**
     * Constructs a new DisposalGuidelineServiceImpl with the specified repositories and model mapper.
     *
     * @param guidelineRepository the repository for disposal guidelines
     * @param categoryRepository the repository for waste categories
     * @param modelMapper the model mapper for converting between entities and DTOs
     */
    @Autowired
    public DisposalGuidelineServiceImpl(
            DisposalGuidelineRepository guidelineRepository,
            WasteCategoryRepository categoryRepository,
            ModelMapper modelMapper) {
        this.guidelineRepository = guidelineRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Retrieves a list of disposal guidelines for a specific waste category.
     *
     * @param categoryId the ID of the waste category
     * @return a list of DisposalGuidelineDTO objects
     * @throws ResourceNotFoundException if the waste category is not found
     */
    @Override
    @Transactional(readOnly = true)
    public List<DisposalGuidelineDTO> getGuidelinesByCategory(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Waste category not found with id: " + categoryId);
        }

        return guidelineRepository.findByCategoryId(categoryId).stream()
                .map(guideline -> modelMapper.map(guideline, DisposalGuidelineDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Creates a new disposal guideline.
     *
     * @param guidelineDTO the DTO containing the details of the guideline to be created
     * @return the created DisposalGuidelineDTO object
     * @throws ResourceNotFoundException if the waste category is not found
     */
    @Override
    public DisposalGuidelineDTO createGuideline(DisposalGuidelineDTO guidelineDTO) {
        WasteCategory category = categoryRepository.findById(guidelineDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Waste category not found with id: " + guidelineDTO.getId()));

        DisposalGuideline guideline = modelMapper.map(guidelineDTO, DisposalGuideline.class);
        guideline.setCategory(category);
        guideline = guidelineRepository.save(guideline);
        return modelMapper.map(guideline, DisposalGuidelineDTO.class);
    }

    /**
     * Updates an existing disposal guideline.
     *
     * @param id the ID of the guideline to be updated
     * @param guidelineDTO the DTO containing the updated details of the guideline
     * @return the updated DisposalGuidelineDTO object
     * @throws ResourceNotFoundException if the disposal guideline or waste category is not found
     */
    @Override
    public DisposalGuidelineDTO updateGuideline(Long id, DisposalGuidelineDTO guidelineDTO) {
        DisposalGuideline existingGuideline = guidelineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disposal guideline not found with id: " + id));

        WasteCategory category = categoryRepository.findById(guidelineDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Waste category not found with id: " + guidelineDTO.getId()));

        modelMapper.map(guidelineDTO, existingGuideline);
        existingGuideline.setCategory(category);
        existingGuideline = guidelineRepository.save(existingGuideline);
        return modelMapper.map(existingGuideline, DisposalGuidelineDTO.class);
    }

    /**
     * Deletes a disposal guideline by its ID.
     *
     * @param id the ID of the guideline to be deleted
     * @throws ResourceNotFoundException if the disposal guideline is not found
     */
    @Override
    public void deleteGuideline(Long id) {
        if (!guidelineRepository.existsById(id)) {
            throw new ResourceNotFoundException("Disposal guideline not found with id: " + id);
        }
        guidelineRepository.deleteById(id);
    }
}