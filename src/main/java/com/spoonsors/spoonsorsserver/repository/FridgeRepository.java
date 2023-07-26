package com.spoonsors.spoonsorsserver.repository;

import com.spoonsors.spoonsorsserver.entity.Fridge;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FridgeRepository {
    private final EntityManager em;

    public List<Fridge> getFridgeItems(String bMemberId){
        return em.createQuery("SELECT f FROM Fridge f where f.bMember.bMember_id= :bMemberId", Fridge.class)
                .setParameter("bMemberId", bMemberId)
                .getResultList();
    }
}
