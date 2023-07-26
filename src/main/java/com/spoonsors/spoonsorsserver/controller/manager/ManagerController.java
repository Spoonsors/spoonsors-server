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
    // 상품 목록 조회
    @GetMapping("/manager/{sMemberId}")
    public List<Ingredients> findAll(){
        return managerService.findAll();
    }

    // 상품 등록
    @PostMapping("/manager/{sMemberId}")
    public String create(@RequestParam Long ingredients_id, String ingredients_name,
                              String product_name, byte[] ingredients_image, Integer price){
        Ingredients ingredient = new Ingredients();
        ingredient.setIngredients_id(ingredients_id);
        ingredient.setIngredients_name(ingredients_name);
        ingredient.setProduct_name(product_name);
        ingredient.setIngredients_image(ingredients_image);
        ingredient.setPrice((price));

        Long ingredientId = managerService.regist(ingredient);
        return ingredientId + "번 상품 등록 완료";
    }
}
