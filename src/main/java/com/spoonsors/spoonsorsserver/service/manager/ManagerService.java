package com.spoonsors.spoonsorsserver.service.manager;

import com.spoonsors.spoonsorsserver.entity.BMember;
import com.spoonsors.spoonsorsserver.entity.Ingredients;
import com.spoonsors.spoonsorsserver.entity.manager.CertificateDto;
import com.spoonsors.spoonsorsserver.entity.manager.IngredientsDto;
import com.spoonsors.spoonsorsserver.repository.*;
import com.spoonsors.spoonsorsserver.service.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ManagerService {

    private final ManagerRepository managerRepository;

    private final BMemberRepository bMemberRepository;
    private final IbMemberRepository ibMemberRepository;
    private final IManagerRepository iManagerRepository;
    private final IIngredientsRepository iIngredientsRepository;

    //식재료 등록
    public Ingredients regist(IngredientsDto ingredientsDto, String img) throws IOException {
        Ingredients addIngredientItem = iManagerRepository.save(ingredientsDto.toEntity());


        addIngredientItem.setIngredients_image(img);
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
    public Ingredients update( Long ingredients_id,  IngredientsDto ingredientsDto, String img){

        Ingredients updateIngredient  = new Ingredients();
        updateIngredient.setIngredients_id(ingredients_id);
        updateIngredient.setIngredients_name(ingredientsDto.getIngredientsName());
        updateIngredient.setProduct_name(ingredientsDto.getProductName());
        updateIngredient.setPrice(ingredientsDto.getPrice());

        updateIngredient.setIngredients_image(img);

        return iIngredientsRepository.save(updateIngredient);
    }

    // 식재료 삭제
    public void remove(Long ingredients_id){
        managerRepository.remove(ingredients_id);
    }

    // 수혜자 증명서 등록 상태 확인
    public List<CertificateDto> certificate(){
        List<BMember> bMember = ibMemberRepository.findAll();
        List<CertificateDto> certificateDtos = new ArrayList<>();
        for(BMember b : bMember){
            CertificateDto certificateDto = new CertificateDto();
            certificateDto.setBMember_id(b.getBMember_id());
            certificateDto.setBMember_name(b.getBMember_name());
            certificateDto.setBMember_birth(b.getBMember_birth());
            certificateDto.setBMember_phoneNumber(b.getBMember_phoneNumber());
            certificateDto.setBMember_address(b.getBMember_address());
            certificateDto.setBMember_certificate(b.getBMember_certificate());
            certificateDto.setIs_verified(b.getIs_verified());
            certificateDtos.add(certificateDto);
        }
        return certificateDtos;
    }

    // 수혜자 증명서 등록 상태 변경
    public String isVerified(String bMember_id){

        bMemberRepository.updateVerified(bMember_id);
        return bMember_id+ " 수혜자 증명서 등록 상태 변경 완료";
    }
}
