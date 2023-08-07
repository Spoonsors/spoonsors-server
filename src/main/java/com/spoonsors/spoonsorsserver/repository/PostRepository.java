package com.spoonsors.spoonsorsserver.repository;

import com.spoonsors.spoonsorsserver.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;

    public void changeRemain(Long post_id){
        Post post = em.find(Post.class, post_id);
        post.setRemain_spon(post.getRemain_spon()-1);
    }

    public void changeState(Long post_id){
        Post post = em.find(Post.class, post_id);
        post.setPost_state(1);
    }

    public void changeReviewState(Long post_id){
        Post post = em.find(Post.class, post_id);
        post.setHas_review(1);
    }
}
