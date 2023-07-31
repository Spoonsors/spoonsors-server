package com.spoonsors.spoonsorsserver.repository;

import com.spoonsors.spoonsorsserver.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {
    private final EntityManager em;
    public List<Post> viewMyPostings(String bMemberId){
        return em.createQuery("select p from Post p where p.bMember.bMember_id =:bMemberId")
                .setParameter("bMemberId", bMemberId)
                .getResultList();
    }
}
