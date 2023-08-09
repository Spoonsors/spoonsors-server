package com.spoonsors.spoonsorsserver.controller.authorize;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spoonsors.spoonsorsserver.entity.authorize.MessageDto;
import com.spoonsors.spoonsorsserver.entity.authorize.SmsResponseDto;
import com.spoonsors.spoonsorsserver.service.authorize.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SmsController {
    private final SmsService smsService;

    @PostMapping("/sms/send/{memberId}")
    public SmsResponseDto sendSms(HttpServletRequest request,@PathVariable String memberId, @RequestBody MessageDto messageDto) throws UnsupportedEncodingException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {

        SmsResponseDto responseDto = smsService.sendSms(request,memberId,messageDto);
        HttpSession session = request.getSession();
        log.info("session main ={}",session.getAttribute("id"));
        log.info("session main ={}",session.getAttribute("token"));

        return responseDto;
    }
}