package com.spoonsors.spoonsorsserver.repository;

import com.spoonsors.spoonsorsserver.entity.SMember;
import com.spoonsors.spoonsorsserver.entity.Spon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SponRepository {

    private final EntityManager em;

    public void putTid(Long spon_id, String tid){
        Spon spon = em.find(Spon.class, spon_id);
        spon.setTid(tid);
    }

    public Optional<Spon> findByTid(String tid) {
        return em.createQuery("SELECT s FROM Spon s WHERE s.tid = :tid", Spon.class)
                .setParameter("tid", tid)
                .getResultList().stream().findAny();
    }
}
