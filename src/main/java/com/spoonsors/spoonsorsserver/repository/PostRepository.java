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

    public String changeState(Long post_id){
        Post post = em.find(Post.class, post_id);
        if(post.getPost_state()==1 && post.getHas_review()==0){
            post.setPost_state(0);
            return "글 마감 취소 완료";
        }else if(post.getPost_state()==1 && post.getHas_review()==1){
            return "글 마감 취소 불가 - 등록된 리뷰가 있습니다.";
        }else{
            post.setPost_state(1);
            return "글 마감 완료";
        }
    }

    public void changeReviewState(Long post_id){
        Post post = em.find(Post.class, post_id);
        post.setHas_review(1);
    }
}
