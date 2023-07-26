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

    //상품 등록
    public Long regist(Ingredients ingredient){
        managerRepository.regist(ingredient);
        return ingredient.getIngredients_id();
    }

    // 모든 상품 조회
    public List<Ingredients> findAll(){
        return managerRepository.findAll();
    }
}
