package com.spoonsors.spoonsorsserver.controller.bMember;

import com.spoonsors.spoonsorsserver.entity.Post;
import com.spoonsors.spoonsorsserver.entity.bMember.PostDto;
import com.spoonsors.spoonsorsserver.service.bMember.PostService;
import com.spoonsors.spoonsorsserver.service.spon.SponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final SponService sponService;

    @PostMapping("/bMember/post/{bMemberId}")
    public String writePost(@PathVariable String bMemberId, @RequestBody PostDto postDto){
        Post post=postService.writePost(bMemberId, postDto);
        sponService.addSpon(postDto.getItem_list(), post.getPost_id());
        return "["+post.getPost_title()+"] 작성 완료";
    }

    //전체 글 보기
    @GetMapping("/viewPosting")
    public List<Post> viewAllPosts(){
        List<Post> posts=postService.viewAllPosts();
        return posts;
    }

    //단일 글 보기
    @GetMapping("/viewPosting/{postId}")
    public Post viewPost(@PathVariable Long postId){
        Post post=postService.viewPost(postId);
        return post;
    }

    //내가 작성한 글 보기
    @GetMapping("/viewMyPosting/{bMemberId}")
    public List<Post> viewMyPosts(@PathVariable String bMemberId){
        List<Post> posts=postService.viewMyPosts(bMemberId);
        return posts;
    }
}
