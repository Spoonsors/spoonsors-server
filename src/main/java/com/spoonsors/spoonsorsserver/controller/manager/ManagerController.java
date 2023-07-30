package com.spoonsors.spoonsorsserver.controller.manager;

import com.spoonsors.spoonsorsserver.entity.Ingredients;
import com.spoonsors.spoonsorsserver.service.manager.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/manager/findByName/{ingredients_name}")
    public Ingredients findByName(@PathVariable String ingredients_name){
        return managerService.findByName(ingredients_name);
    }

    // 식재료 등록
    @PostMapping("/manager/create")
    public String create(@RequestParam  byte[] ingredients_image,
                         String ingredients_name, Integer price, String product_name){
        Ingredients ingredient = new Ingredients();
        ingredient.setIngredients_name(ingredients_name);
        ingredient.setProduct_name(product_name);
        ingredient.setIngredients_image(ingredients_image);
        ingredient.setPrice((price));

        Long ingredientId = managerService.regist(ingredient);
        return ingredientId + "번 식재료 등록 완료";
    }

    //식재로 수정
    @PutMapping("/manager/update")
    public String update(@RequestParam Long ingredients_id, String ingredients_name,
                         String product_name, byte[] ingredients_image, Integer price){
        Long ingredientId = managerService.update(ingredients_id,ingredients_image,
        ingredients_name,price,product_name);
        return ingredientId + "번 식재료 수정 완료";
    }

    //식재료 삭제
    @DeleteMapping("/manager/delete")
    public String delete(@RequestParam Long ingredients_id){
        managerService.remove(ingredients_id);
        return ingredients_id + "번 식재료 삭제 완료";
    }
}
