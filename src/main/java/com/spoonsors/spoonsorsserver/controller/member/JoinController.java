package com.spoonsors.spoonsorsserver.controller.member;

import com.spoonsors.spoonsorsserver.service.member.JoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
@RequiredArgsConstructor
public class JoinController {
    private final JoinService joinService;

    @GetMapping("/join/checkId")
    public String checkId(@RequestParam String id){
        String txt=joinService.checkId(id);
        return txt;
    }

    @GetMapping("/join/checkNickname")
    public String checkNickname(@RequestParam String nickname){
        String txt=joinService.checkNickname(nickname);
        return txt;
    }

    //카카오 로그인으로 회원가입 및 로그인
    @GetMapping("/join/kakao")
    public String kakaoLogin(@RequestParam String code) throws Throwable {

        String accessToken= joinService.getAccessToken(code);
        HashMap<String, String> info= joinService.getUserInfo(accessToken);

        return joinService.loginOrJoin(info);
    }
}
