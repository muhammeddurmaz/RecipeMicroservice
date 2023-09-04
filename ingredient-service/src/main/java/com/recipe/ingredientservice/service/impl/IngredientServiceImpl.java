package com.recipe.ingredientservice.service.impl;

import com.recipe.ingredientservice.dto.CreateIngredientRequest;
import com.recipe.ingredientservice.dto.IngredientDTO;
import com.recipe.ingredientservice.dto.mapper.IngredientMapper;
import com.recipe.ingredientservice.exception.BadRequestAlertException;
import com.recipe.ingredientservice.model.Ingredient;
import com.recipe.ingredientservice.repository.IngredientRepository;
import com.recipe.ingredientservice.service.IngredientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
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
//        ingredientDTO.setId(null);
        ingredientDTO.setName(ingredientRequest.getName());
        ingredientDTO.setCategory(ingredientRequest.getCategory());
        Ingredient ingredient = ingredientMapper.toEntity(ingredientDTO);
        ingredient = ingredientRepository.save(ingredient);
        return ingredientMapper.toDto(ingredient);
    }

    @Override
    public IngredientDTO findOneById(Long id) {
        return ingredientRepository.findById(id)
                .map(ingredientMapper::toDto)
                .orElseThrow(() -> new BadRequestAlertException("Ingredient could not found by id " + id));
    }

    @Override
    public List<IngredientDTO> findAllIds(List<Long> ids) {
        return ingredientRepository.findAllById(ids)
                .stream()
                .map(ingredientMapper::toDto)
                .collect(Collectors.toList());
    }
}
