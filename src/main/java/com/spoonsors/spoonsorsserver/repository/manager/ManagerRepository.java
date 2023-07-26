package com.spoonsors.spoonsorsserver.repository.manager;

import com.spoonsors.spoonsorsserver.entity.Ingredients;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ManagerRepository {

    private final EntityManager em;

    //상품 등록
    public void regist(Ingredients ingredient){
        em.persist(ingredient);
    }

    //모든 상품 조회
    public List<Ingredients> findAll() {
        return em.createQuery("SELECT u FROM Ingredients u", Ingredients.class)
                .getResultList();
    }
}
