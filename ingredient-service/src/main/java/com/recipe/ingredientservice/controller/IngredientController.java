package com.recipe.ingredientservice.controller;

import com.recipe.ingredientservice.dto.CreateIngredientRequest;
import com.recipe.ingredientservice.dto.IngredientDTO;
import com.recipe.ingredientservice.dto.ResponseDTO;
import com.recipe.ingredientservice.service.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class IngredientController {
    private static final String ENTITY_NAME = "IngredientEntity";
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping("/ingredient")
    public ResponseEntity<ResponseDTO<IngredientDTO>> save(@RequestBody CreateIngredientRequest request){
        IngredientDTO savedEntity = ingredientService.save(request);
        ResponseDTO<IngredientDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setData(savedEntity);
        responseDTO.setMessage("Create Success",ENTITY_NAME);
        responseDTO.setSuccess(true);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/ingredient/{id}")
    public ResponseEntity<ResponseDTO<IngredientDTO>> getIngredientId(@PathVariable(value = "id") Long id){
        IngredientDTO ingredientDTO = ingredientService.findOneById(id);
        ResponseDTO<IngredientDTO>  responseDTO = new ResponseDTO<>();
        responseDTO.setData(ingredientDTO);
        responseDTO.setMessage("Get One Entity Success",ENTITY_NAME);
        responseDTO.setSuccess(true);
        return ResponseEntity.ok().body(responseDTO);
    }
}
