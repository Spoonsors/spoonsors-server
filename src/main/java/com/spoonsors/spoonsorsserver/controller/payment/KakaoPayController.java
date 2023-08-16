package com.spoonsors.spoonsorsserver.controller.payment;

import com.spoonsors.spoonsorsserver.entity.payment.Order;
import com.spoonsors.spoonsorsserver.service.payment.KakaoPayService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
public class KakaoPayController {

    //@Setter(onMethod_ = @Autowired)
    private final KakaoPayService kakaoPayService;

    // 카카오페이결제 요청
    @PostMapping("/sMember/kakaoPay")
    public String  payReady(@RequestBody Order order) {

        return kakaoPayService.payReady(order);
    }

    //결제 완료
    @GetMapping("/sMember/kakaoPay/completed")
    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token) {

        kakaoPayService.payApprove(pg_token);
        //todo DB저장 코드
        return "결제 완료";
    }

    // 결제 취소시 실행 url
    @GetMapping("/sMember/kakaoPay/cancel")
    public String payCancel() {
        return "결제 취소"; //todo 수정
    }

    // 결제 실패시 실행 url
    @GetMapping("/sMember/kakaoPay/fail")
    public String payFail() {
        return "결제 실패"; //todo 수정
    }
}
