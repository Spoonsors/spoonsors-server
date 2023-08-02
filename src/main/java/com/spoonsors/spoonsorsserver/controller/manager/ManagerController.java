package com.spoonsors.spoonsorsserver.controller.manager;

import com.spoonsors.spoonsorsserver.entity.Ingredients;
import com.spoonsors.spoonsorsserver.entity.manager.IngredientsDto;
import com.spoonsors.spoonsorsserver.service.manager.ManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;
    // 식재료 목록 조회
    @GetMapping("/manager/findAll")
    public List<Ingredients> findAll(){
        return managerService.findAll();
    }

    // 이름으로 식재료 조회
    @GetMapping("/manager/findByName")
    public Ingredients findByName(@RequestParam String ingredients_name){
        return managerService.findByName(ingredients_name);
    }

    // 식재료 등록
    @PostMapping(value ="/manager/create", consumes = {MediaType.APPLICATION_JSON_VALUE, "multipart/form-data"})
    public Ingredients create(@RequestPart IngredientsDto ingredientsDto, @RequestPart(value = "img", required = false) MultipartFile img){
        Ingredients ingredient = null;
        try{
            ingredient = managerService.regist(ingredientsDto, img);
        }catch (IOException e){
            e.printStackTrace();
        }
        return ingredient;
    }

    //식재로 수정
    @PutMapping("/manager/update/{ingredients_id}")
    public Ingredients update(@PathVariable Long ingredients_id, @RequestPart IngredientsDto ingredientsDto, @RequestPart(value = "img", required = false) MultipartFile img){
        Ingredients ingredient = null;
        ingredient = managerService.update(ingredients_id, ingredientsDto, img);
        return ingredient;
    }

    //식재료 삭제
    @DeleteMapping("/manager/delete/{ingredients_id}")
    public String delete(@PathVariable Long ingredients_id){
        managerService.remove(ingredients_id);
        return ingredients_id + "번 식재료 삭제 완료";
    }
}
