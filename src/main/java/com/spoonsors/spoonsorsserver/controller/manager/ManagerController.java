package com.spoonsors.spoonsorsserver.controller.manager;

import com.spoonsors.spoonsorsserver.entity.Ingredients;
import com.spoonsors.spoonsorsserver.service.manager.ManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/manager/create")
    public String create(@RequestBody Ingredients ingredients){
        Long ingredientId = managerService.regist(ingredients);
        return ingredientId + "번 식재료 등록 완료";
    }

    //식재로 수정
    @PutMapping("/manager/update")
    public String update(@RequestBody Ingredients ingredients){
        Long ingredientId = managerService.update(ingredients);
        return ingredientId + "번 식재료 수정 완료";
    }

    //식재료 삭제
    @DeleteMapping("/manager/delete/{ingredients_id}")
    public String delete(@PathVariable Long ingredients_id){
        managerService.remove(ingredients_id);
        return ingredients_id + "번 식재료 삭제 완료";
    }
}
