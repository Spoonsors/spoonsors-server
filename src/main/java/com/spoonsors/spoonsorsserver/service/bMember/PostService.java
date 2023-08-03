package com.spoonsors.spoonsorsserver.service.bMember;

import com.spoonsors.spoonsorsserver.entity.BMember;
import com.spoonsors.spoonsorsserver.entity.Post;
import com.spoonsors.spoonsorsserver.entity.Review;
import com.spoonsors.spoonsorsserver.entity.bMember.ViewPostDto;
import com.spoonsors.spoonsorsserver.entity.bMember.WritePostDto;
import com.spoonsors.spoonsorsserver.repository.IPostRepository;
import com.spoonsors.spoonsorsserver.repository.IReviewRepository;
import com.spoonsors.spoonsorsserver.repository.IbMemberRepository;
import com.spoonsors.spoonsorsserver.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final IPostRepository iPostRepository;
    private final IbMemberRepository ibMemberRepository;
    private final IReviewRepository iReviewRepository;
    private final PostRepository postRepository;

    //글 작성
    public Post writePost(String bMemberId, WritePostDto writePostDto){
        Optional<BMember> optionalBMember =ibMemberRepository.findById(bMemberId);
        BMember bMember = optionalBMember.get();
        writePostDto.setBMember(bMember);
        Post post=iPostRepository.save(writePostDto.toEntity());
        return post;
    }

    //전체 글 조회
    public List<Post> viewAllPosts(){
        List<Post> posts=iPostRepository.findAll();
        return posts;
    }

    //단일 글 조회
    public ViewPostDto viewPost(Long postId){
        Optional<Post> optionalPost=iPostRepository.findById(postId);
        Post post=optionalPost.get();
        ViewPostDto viewPostDto = new ViewPostDto();
        viewPostDto.setPost(post);
//        Optional<Review> optionalReview=iReviewRepository.findById(post.getPost_id());
//        Review review = optionalReview.get();
//        viewPostDto.setReview(review);
        if(post.getHas_review()==1){
            Optional<Review> optionalReview=iReviewRepository.findById(post.getPost_id());
            Review review = optionalReview.get();
            viewPostDto.setReview(review);
        }
        return viewPostDto;
    }

    //내가 작성한 글 보기
    public List<Post> viewMyPosts(String bMemberId){
        Optional<BMember> optionalBMember =ibMemberRepository.findById(bMemberId);
        BMember bMember = optionalBMember.get();
        return bMember.getPosts();
    }

    //글 상태 변경
    public String changePostState(Long post_id){
        postRepository.changeState(post_id);
        return "변경 완료";
    }
}
