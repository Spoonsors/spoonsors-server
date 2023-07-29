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
    public Long update(Long ingredients_id, byte[] ingredients_image,
                       String ingredients_name, Integer price, String product_name){
        Ingredients updateIngredient  = managerRepository.findById(ingredients_id);
        updateIngredient.setIngredients_name(ingredients_name);
        updateIngredient.setProduct_name(product_name);
        updateIngredient.setIngredients_image(ingredients_image);
        updateIngredient.setPrice(price);

        return updateIngredient.getIngredients_id();
    }

    // 식재료 삭제
    public void remove(Long ingredients_id){
        managerRepository.remove(ingredients_id);
    }
}
