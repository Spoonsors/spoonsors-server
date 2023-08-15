package com.spoonsors.spoonsorsserver.repository;

import com.spoonsors.spoonsorsserver.entity.BMember;
import com.spoonsors.spoonsorsserver.entity.Fridge;
import com.spoonsors.spoonsorsserver.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Map;
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

    public Optional<BMember> findId(String name, String phoneNum){
        return em.createQuery("SELECT b FROM BMember b WHERE b.bMember_name = :name and b.bMember_phoneNumber= : phoneNum", BMember.class)
                .setParameter("name",name)
                .setParameter("phoneNum", phoneNum)
                .getResultList().stream().findAny();
    }
    public void putToken(Map<String, String> token){
        BMember bMember = em.find(BMember.class, token.get("id"));
        bMember.setToken(token.get("token"));
    }
}
