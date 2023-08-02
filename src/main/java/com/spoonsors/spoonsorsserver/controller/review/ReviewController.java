package com.spoonsors.spoonsorsserver.controller.review;

import com.spoonsors.spoonsorsserver.entity.Review;
import com.spoonsors.spoonsorsserver.entity.review.ReviewDto;
import com.spoonsors.spoonsorsserver.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;


    //리뷰 둥록
    @PostMapping(value = "/review/create/{post_id}", consumes = {MediaType.APPLICATION_JSON_VALUE, "multipart/form-data"})
    public Review wirteReview(@PathVariable Long post_id, @RequestPart ReviewDto reviewDto, @RequestPart(value = "img", required = false) MultipartFile img)throws IOException{
        Review review = null;
        try {
            review = reviewService.writeReview(post_id, reviewDto, img);
        }catch (IOException e){
            e.printStackTrace();
        }

        return review;
    }
    // 내가 작성한 리뷰 확인
    /*@GetMapping("/review/findMyReview/{post_id}")
    public List<Review> findMyReview(@PathVariable Long post_id){
        List<Review> myReview = null;
        try{
            myReview = reviewService.findMyReview(post_id);
        }catch (IOException e){
            e.printStackTrace();
        }
        return List<myReview>;
    }
    */
}
