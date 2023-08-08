package com.spoonsors.spoonsorsserver.controller.member;

import com.spoonsors.spoonsorsserver.entity.bMember.BMemberSignUpDto;
import com.spoonsors.spoonsorsserver.service.member.BMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BMemberController {

    private final BMemberService bMemberService;

    @PostMapping("/join/bMember")
    public String join(@RequestBody BMemberSignUpDto dto) throws Exception{
        String id=bMemberService.signUp(dto);
        return id+" 회원가입 완료";
    }

    @PostMapping("/login/bMember")
    public String login(@RequestBody Map<String, String> member) {
        return bMemberService.login(member);
    }
}
