package com.spoonsors.spoonsorsserver.controller.payment;

import com.spoonsors.spoonsorsserver.customException.ApiException;
import com.spoonsors.spoonsorsserver.customException.ExceptionEnum;
import com.spoonsors.spoonsorsserver.entity.payment.ApproveRequestPayDto;
import com.spoonsors.spoonsorsserver.service.payment.KakaoPayService;
import com.spoonsors.spoonsorsserver.service.spon.SponService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;
    private final SponService sponService;


    // 카카오페이결제 요청
    @PostMapping("/sMember/kakaoPay/{spon_id}/{sMemberId}")
    public ResponseEntity<?> payReady(@PathVariable Long spon_id, @PathVariable String sMemberId){
        //상태 확인하고
        String txt= sponService.checkSpon(spon_id);
        if(txt.equals("후원 가능")){
            //상태 괜찮으면 카카오서비스 payReady 실행
            String link= kakaoPayService.payReady(sMemberId, spon_id);
            if(link=="결제 요청 실패"){
                throw new ApiException(ExceptionEnum.PAY01); //결제 요청 실패
            }
            return ResponseEntity.status(HttpStatus.OK).body(link);
        }else if(txt.equals("이미 후원이 완료된 물품입니다.")){
            throw new ApiException(ExceptionEnum.SPON01);
        }else {
            throw new ApiException(ExceptionEnum.PAY01); //결제 요청 실패
        }
    }

    //결제 완료
    @GetMapping("/sMember/kakaoPay/completed")
    public ResponseEntity<?>  kakaoPaySuccess(@RequestParam("pg_token") String pg_token) {
        ApproveRequestPayDto approveRequestPayDto = kakaoPayService.payApprove(pg_token);
        if(approveRequestPayDto!=null){
            //스폰 내역 저장
            sponService.applySpon(approveRequestPayDto.getTid(), approveRequestPayDto.getPartner_user_id());
            return ResponseEntity.status(HttpStatus.OK).body("결제 완료");
        }else{
            throw new ApiException(ExceptionEnum.PAY02); //결제 실패
        }
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
