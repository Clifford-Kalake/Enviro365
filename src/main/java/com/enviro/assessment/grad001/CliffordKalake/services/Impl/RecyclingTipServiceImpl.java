package com.enviro.assessment.grad001.CliffordKalake.services.impl;

import com.enviro.assessment.grad001.CliffordKalake.dtos.RecyclingTipDTO;
import com.enviro.assessment.grad001.CliffordKalake.exception.ResourceNotFoundException;
import com.enviro.assessment.grad001.CliffordKalake.model.RecyclingTip;
import com.enviro.assessment.grad001.CliffordKalake.repositories.RecyclingTipRepository;
import com.enviro.assessment.grad001.CliffordKalake.services.RecyclingTipService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecyclingTipServiceImpl implements RecyclingTipService {

    private final RecyclingTipRepository tipRepository;
    private final ModelMapper modelMapper;

    /**
     * Constructs a new RecyclingTipServiceImpl with the specified repository and model mapper.
     *
     * @param tipRepository the repository for recycling tips
     * @param modelMapper the model mapper for converting between entities and DTOs
     */
    @Autowired
    public RecyclingTipServiceImpl(RecyclingTipRepository tipRepository, ModelMapper modelMapper) {
        this.tipRepository = tipRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Retrieves all recycling tips.
     *
     * @return a list of RecyclingTipDTO objects representing all recycling tips
     */
    @Override
    public List<RecyclingTipDTO> getAllTips() {
        return tipRepository.findAll().stream()
                .map(tip -> modelMapper.map(tip, RecyclingTipDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Creates a new recycling tip.
     *
     * @param tipDTO the DTO object containing the details of the recycling tip to be created
     * @return the created RecyclingTipDTO object
     */
    @Override
    public RecyclingTipDTO createTip(RecyclingTipDTO tipDTO) {
        RecyclingTip tip = modelMapper.map(tipDTO, RecyclingTip.class);
        tip = tipRepository.save(tip);
        return modelMapper.map(tip, RecyclingTipDTO.class);
    }

    /**
     * Deletes a recycling tip by its ID.
     *
     * @param id the ID of the recycling tip to be deleted
     * @throws ResourceNotFoundException if the recycling tip with the specified ID does not exist
     */
    @Override
    public void deleteTip(Long id) {
        if (!tipRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recycling tip not found with id: " + id);
        }
        tipRepository.deleteById(id);
    }
}