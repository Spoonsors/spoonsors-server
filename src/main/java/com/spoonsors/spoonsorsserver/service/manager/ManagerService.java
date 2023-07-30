package com.spoonsors.spoonsorsserver.service.manager;

import com.spoonsors.spoonsorsserver.entity.Ingredients;
import com.spoonsors.spoonsorsserver.repository.manager.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ManagerService {

    private final ManagerRepository managerRepository;

    //식재료 등록
    public Long regist(Ingredients ingredient){
        managerRepository.regist(ingredient);
        return ingredient.getIngredients_id();
    }

    // 식재료 전체 조회
    public List<Ingredients> findAll(){
        return managerRepository.findAll();
    }

    //name으로 식재료 검색
    public Ingredients findByName(String name){
        return managerRepository.findByName(name);
    }

    //식재료 수정
    public Long update(Ingredients ingredients){

        Ingredients updateIngredient  = managerRepository.findById(ingredients.getIngredients_id());
        updateIngredient.setIngredients_name(ingredients.getIngredients_name());
        updateIngredient.setProduct_name(ingredients.getProduct_name());
        updateIngredient.setIngredients_image(ingredients.getIngredients_image());
        updateIngredient.setPrice(ingredients.getPrice());

        return updateIngredient.getIngredients_id();
    }

    // 식재료 삭제
    public void remove(Long ingredients_id){
        managerRepository.remove(ingredients_id);
    }
}
