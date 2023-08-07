package com.spoonsors.spoonsorsserver.repository;

import com.spoonsors.spoonsorsserver.entity.BMember;
import com.spoonsors.spoonsorsserver.entity.Fridge;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BMemberRepository {
    private final EntityManager em;

    public Optional<BMember> findByNickname(String nickname) {
        return em.createQuery("SELECT b FROM BMember b WHERE b.bMember_nickname = :nickname", BMember.class)
                .setParameter("nickname", nickname)
                .getResultList().stream().findAny();
    }
}
