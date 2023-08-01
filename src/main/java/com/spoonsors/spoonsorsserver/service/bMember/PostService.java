package com.spoonsors.spoonsorsserver.service.bMember;

import com.spoonsors.spoonsorsserver.entity.BMember;
import com.spoonsors.spoonsorsserver.entity.Post;
import com.spoonsors.spoonsorsserver.entity.bMember.PostDto;
import com.spoonsors.spoonsorsserver.repository.IPostRepository;
import com.spoonsors.spoonsorsserver.repository.IbMemberRepository;
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

    //글 작성
    public Post writePost(String bMemberId, PostDto postDto){
        Optional<BMember> optionalBMember =ibMemberRepository.findById(bMemberId);
        BMember bMember = optionalBMember.get();
        postDto.setBMember(bMember);
        Post post=iPostRepository.save(postDto.toEntity());
        return post;
    }

    //전체 글 조회
    public List<Post> viewAllPosts(){
        List<Post> posts=iPostRepository.findAll();
        return posts;
    }

    //단일 글 조회
    public Post viewPost(Long postId){
        Optional<Post> optionalPost=iPostRepository.findById(postId);
        Post post=optionalPost.get();
        return post;
    }

    //내가 작성한 글 보기
    public List<Post> viewMyPosts(String bMemberId){
        Optional<BMember> optionalBMember =ibMemberRepository.findById(bMemberId);
        BMember bMember = optionalBMember.get();
        return bMember.getPosts();
    }
}
