package com.spoonsors.spoonsorsserver.controller.member;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spoonsors.spoonsorsserver.entity.authorize.MessageDto;
import com.spoonsors.spoonsorsserver.service.authorize.SmsService;
import com.spoonsors.spoonsorsserver.service.member.JoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class JoinController {
    private final JoinService joinService;
    private final SmsService smsService;

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
    // 아이디 찾기
    @PostMapping("/join/findId")
    public String findId(HttpServletRequest request, @RequestBody Map<String,String> find) throws UnsupportedEncodingException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        return joinService.findId(request, find.get("name"),find.get("phoneNum"));
    }
    //아이디 찾기 인증확인
    @PostMapping("/join/verify")
    public String verifyFindId(HttpServletRequest request,@RequestBody Map<String,String> verification) throws UnsupportedEncodingException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        String verify;
        verify = smsService.verifySms(request.getSession(),verification.get("phoneNum"), verification.get("code"));
        if(verify == null) {
            return "인증번호가 맞지 않습니다.";
        }else{
            verify = joinService.verifyFindId(verification.get("name"),verification.get("phoneNum"));
            request.getSession().invalidate();
            return verify;
        }
    }
}
