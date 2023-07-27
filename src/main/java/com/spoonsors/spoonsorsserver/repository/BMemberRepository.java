package com.spoonsors.spoonsorsserver.repository;

import com.spoonsors.spoonsorsserver.entity.BMember;
import com.spoonsors.spoonsorsserver.entity.Fridge;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class BMemberRepository {
    private final EntityManager em;

    public BMember findById(String bMember_id) {
        return em.find(BMember.class, bMember_id);
    }
}
