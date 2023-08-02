package com.spoonsors.spoonsorsserver.service.manager;

import com.spoonsors.spoonsorsserver.entity.Ingredients;
import com.spoonsors.spoonsorsserver.entity.manager.IngredientsDto;
import com.spoonsors.spoonsorsserver.repository.IIngredientsRepository;
import com.spoonsors.spoonsorsserver.repository.IManagerRepository;
import com.spoonsors.spoonsorsserver.repository.ManagerRepository;
import com.spoonsors.spoonsorsserver.service.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final IManagerRepository iManagerRepository;
    private final IIngredientsRepository iIngredientsRepository;

    //식재료 등록
    public Ingredients regist(IngredientsDto ingredientsDto, MultipartFile img) throws IOException {
        Ingredients addIngredientItem = iManagerRepository.save(ingredientsDto.toEntity());

        if(img!=null && !img.isEmpty()){
            addIngredientItem.setIngredients_image(ImageUtils.compressImage(img.getBytes()));
        }
        return addIngredientItem;
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
    public Ingredients update( Long ingredients_id,  IngredientsDto ingredientsDto, MultipartFile img){

        Ingredients updateIngredient  = new Ingredients();
        updateIngredient.setIngredients_id(ingredients_id);
        updateIngredient.setIngredients_name(ingredientsDto.getIngredientsName());
        updateIngredient.setProduct_name(ingredientsDto.getProductName());
        updateIngredient.setPrice(ingredientsDto.getPrice());

        if(img!=null && !img.isEmpty()){
            try {
                updateIngredient.setIngredients_image(ImageUtils.compressImage(img.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return iIngredientsRepository.save(updateIngredient);
    }

    // 식재료 삭제
    public void remove(Long ingredients_id){
        managerRepository.remove(ingredients_id);
    }
}
