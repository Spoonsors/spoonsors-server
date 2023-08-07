package com.spoonsors.spoonsorsserver.controller.bMember;

import com.spoonsors.spoonsorsserver.entity.bMember.BMemberSignUpDto;
import com.spoonsors.spoonsorsserver.service.bMember.BMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BMemberController {

    private final BMemberService bMemberService;

    @PostMapping("/join/sMember")
    public String join(@RequestBody BMemberSignUpDto dto) throws Exception{
        String id=bMemberService.signUp(dto);
        return id+" 회원가입 완료";
    }
}
