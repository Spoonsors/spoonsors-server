package com.spoonsors.spoonsorsserver.controller.member;

import com.spoonsors.spoonsorsserver.service.member.JoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
