package com.spoonsors.spoonsorsserver.service.spon;

import com.spoonsors.spoonsorsserver.entity.Ingredients;
import com.spoonsors.spoonsorsserver.entity.Post;
import com.spoonsors.spoonsorsserver.entity.Spon;
import com.spoonsors.spoonsorsserver.repository.IPostRepository;
import com.spoonsors.spoonsorsserver.repository.ISponRepository;
import com.spoonsors.spoonsorsserver.repository.manager.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class SponService {

    private final ManagerRepository managerRepository;
    private final IPostRepository iPostRepository;
    private final ISponRepository iSponRepository;

    public void addSpon(List<String> itemlist, Long postId){
        for(int i=0;i<itemlist.size();i++){
            Ingredients ingredient = managerRepository.findByName(itemlist.get(i));
            Optional<Post> optionalPost =iPostRepository.findById(postId);
            Post post = optionalPost.get();

            Spon spon=new Spon();
            spon.setSpon_date(post.getPost_date());
            spon.setSpon_state(0);
            spon.setIngredients(ingredient);
            spon.setPost(post);
            spon.setSMember(null);

            iSponRepository.save(spon);
        }
    }
}
