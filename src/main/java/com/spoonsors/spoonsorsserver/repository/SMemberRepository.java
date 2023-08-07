package com.spoonsors.spoonsorsserver.repository;

import com.spoonsors.spoonsorsserver.entity.SMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SMemberRepository {

    private final EntityManager em;

    public Optional<SMember> findByNickname(String nickname) {
        return em.createQuery("SELECT s FROM SMember s WHERE s.sMember_nickname = :nickname", SMember.class)
                .setParameter("nickname", nickname)
                .getResultList().stream().findAny();
    }
}
