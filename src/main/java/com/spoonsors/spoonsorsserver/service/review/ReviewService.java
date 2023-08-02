package com.spoonsors.spoonsorsserver.service.review;

import com.spoonsors.spoonsorsserver.entity.BMember;
import com.spoonsors.spoonsorsserver.entity.Post;
import com.spoonsors.spoonsorsserver.entity.Review;
import com.spoonsors.spoonsorsserver.entity.review.ReviewDto;
import com.spoonsors.spoonsorsserver.repository.IPostRepository;
import com.spoonsors.spoonsorsserver.repository.IReviewRepository;
import com.spoonsors.spoonsorsserver.service.ImageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final IReviewRepository iReviewRepository;
    private final IPostRepository iPostRepository;

    //리뷰 작성
    public Review writeReview(Long postId, ReviewDto reviewDto, MultipartFile img)throws IOException {
        Optional<Post> optionalPost =iPostRepository.findById(postId);
        Post post = optionalPost.get();
        reviewDto.setPost(post);
        Review addRreview = iReviewRepository.save(reviewDto.toEntity());

        if(img!=null && !img.isEmpty()){
            addRreview.setReview_img(ImageUtils.compressImage(img.getBytes()));
        }
        return addRreview;
    }

    // 내가 작성한 리뷰 확인
    //public List<Review> findMyReview(Long postId){

    //}
}
