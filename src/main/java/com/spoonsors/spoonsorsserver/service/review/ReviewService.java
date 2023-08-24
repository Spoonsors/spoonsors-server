package com.spoonsors.spoonsorsserver.service.review;

import ch.qos.logback.core.joran.spi.ConsoleTarget;
import com.spoonsors.spoonsorsserver.entity.BMember;
import com.spoonsors.spoonsorsserver.entity.Post;
import com.spoonsors.spoonsorsserver.entity.Review;
import com.spoonsors.spoonsorsserver.entity.review.ReviewDto;
import com.spoonsors.spoonsorsserver.repository.*;
import com.spoonsors.spoonsorsserver.service.ImageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final IReviewRepository iReviewRepository;
    private final IPostRepository iPostRepository;
    private final IbMemberRepository ibMemberRepository;
    private final PostRepository postRepository;

    //리뷰 작성
    public Review writeReview(Long postId, String reviewTxt, String img)throws IOException {

        Date date = new Date();

        Optional<Post> optionalPost =iPostRepository.findById(postId);
        Post post = optionalPost.get();
        if(post.getPost_state() == 0) {throw new IOException("후원 마감되지 않은 글은 리뷰 작성 불가능 합니다.");}
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setReview_txt(reviewTxt);
        reviewDto.setPost(post);
        reviewDto.setReview_date(date);

        //리뷰 저장
        Review addRreview = iReviewRepository.save(reviewDto.toEntity());
        addRreview.setReview_img(img);

        postRepository.changeReviewState(postId);
        postRepository.canPost(post.getBMember().getBMember_id());
        return addRreview;

    }

    // 내가 작성한 리뷰 확인
    public List<Review> findMyReview(String bMemberId){
        Optional<BMember> optionalBMember = ibMemberRepository.findById(bMemberId);
        List<Post> postList = optionalBMember.get().getPosts();
        List<Review> myReviewList = new ArrayList<>();
        for(Post p : postList){

            if(p.getHas_review().equals(1)){
                Optional<Review> optionalReview = iReviewRepository.findById(p.getPost_id());
                Review myReview = optionalReview.get();

                myReviewList.add(myReview);
            }
        }
        return myReviewList;
    }
}
