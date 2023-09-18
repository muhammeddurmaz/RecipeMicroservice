package com.recipe.ingredientservice.service.impl;

import com.recipe.ingredientservice.dto.CreateIngredientRequest;
import com.recipe.ingredientservice.dto.IngredientDTO;
import com.recipe.ingredientservice.dto.mapper.IngredientMapper;
import com.recipe.ingredientservice.exception.BadRequestAlertException;
import com.recipe.ingredientservice.model.Ingredient;
import com.recipe.ingredientservice.repository.IngredientRepository;
import com.recipe.ingredientservice.service.IngredientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, IngredientMapper ingredientMapper) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientMapper = ingredientMapper;
    }

    @Override
    public IngredientDTO save(CreateIngredientRequest ingredientRequest) {
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setName(ingredientRequest.getName());
        Ingredient ingredient = ingredientMapper.toEntity(ingredientDTO);
        ingredient = ingredientRepository.save(ingredient);
        return ingredientMapper.toDto(ingredient);
    }

    @Override
    @Transactional(readOnly = true)
    public IngredientDTO findOneById(Long id) {
        return ingredientRepository.findById(id)
                .map(ingredientMapper::toDto)
                .orElseThrow(() -> new BadRequestAlertException("Ingredient could not found by id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<IngredientDTO> findAllIds(Set<Long> ids) {
        return ingredientRepository.findAllById(ids)
                .stream()
                .map(ingredientMapper::toDto)
                .collect(Collectors.toList());
    }
}
