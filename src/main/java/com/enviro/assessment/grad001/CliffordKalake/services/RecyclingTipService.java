package com.enviro.assessment.grad001.CliffordKalake.services;

import com.enviro.assessment.grad001.CliffordKalake.dtos.RecyclingTipDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public interface RecyclingTipService {
    List<RecyclingTipDTO> getAllTips();
    RecyclingTipDTO createTip(RecyclingTipDTO tipDTO);
    void deleteTip(Long id);
}