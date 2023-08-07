package com.spoonsors.spoonsorsserver.controller.sMember;

import com.spoonsors.spoonsorsserver.entity.sMember.SMemberSignUpDto;
import com.spoonsors.spoonsorsserver.service.sMember.SMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SMemberController {
    private final SMemberService sMemberService;

    @PostMapping("/join/sMember")
    public String join(@RequestBody SMemberSignUpDto dto) throws Exception{
        String id=sMemberService.signUp(dto);
        return id+" 회원가입 완료";
    }
}
