package com.spoonsors.spoonsorsserver.controller.payment;

import com.spoonsors.spoonsorsserver.entity.payment.ApproveRequestPayDto;
import com.spoonsors.spoonsorsserver.entity.payment.Order;
import com.spoonsors.spoonsorsserver.entity.payment.RequestPayDto;
import com.spoonsors.spoonsorsserver.service.payment.KakaoPayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;
    // 카카오페이결제 요청
    @GetMapping("/sMember/pay")
    public RequestPayDto payReady(@RequestBody Order order) {

        // 카카오 결제 준비하기	- 결제요청 service 실행.
        RequestPayDto readyResponse = kakaoPayService.payReady(order);
        // 요청처리후 받아온 결재고유 번호(tid)를 모델에 저장
        //model.addAttribute("tid", readyResponse.getTid());
        log.info("결재고유 번호: " + readyResponse.getTid());
        // Order정보를 모델에 저장
        //model.addAttribute("order",order);

        return readyResponse; // 클라이언트에 보냄.(tid,next_redirect_pc_url이 담겨있음.)

    }

    // 결제승인요청
    @GetMapping("/sMember/pay/completed")
    public String payCompleted(@RequestParam("pg_token") String pgToken, @RequestParam("tid") String tid,  Model model) {

        log.info("결제승인 요청을 인증하는 토큰: " + pgToken);
        log.info("결재고유 번호: " + tid);

        // 카카오 결재 요청하기
        ApproveRequestPayDto approveResponse = kakaoPayService.payApprove(tid, pgToken);

        log.info("응답 결과", approveResponse.getItem_name());
        // 5. payment 저장
        //	orderNo, payMathod, 주문명.
        // - 카카오 페이로 넘겨받은 결재정보값을 저장.
//        Payment payment = Payment.builder()
//                .paymentClassName(approveResponse.getItem_name())
//                .payMathod(approveResponse.getPayment_method_type())
//                .payCode(tid)
//                .build();
//
//        orderService.saveOrder(order,payment);

        return "redirect:/orders";
    }
    // 결제 취소시 실행 url
    @GetMapping("/sMember/pay/cancel")
    public String payCancel() {
        return "redirect:/carts";
    }

    // 결제 실패시 실행 url
    @GetMapping("/sMember/pay/fail")
    public String payFail() {
        return "redirect:/carts";
    }
}
