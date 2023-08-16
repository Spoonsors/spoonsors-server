package com.spoonsors.spoonsorsserver.service.payment;

import com.spoonsors.spoonsorsserver.entity.payment.ApproveRequestPayDto;
import com.spoonsors.spoonsorsserver.entity.payment.Order;
import com.spoonsors.spoonsorsserver.entity.payment.RequestPayDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@Service
public class KakaoPayService {


    public RequestPayDto payReady(Order order) {

       log.info("product_name::  ", order.getProduct_name());
        log.info("price::  ", order.getPrice());

        // 카카오가 요구한 결제요청request값을 담아줍니다.
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", "TC0ONETIME"); //가맹점 코드. 10자 실제 제휴 후 받아야함
        parameters.add("partner_order_id", "partner_order_id"); // 가맹점의 주문번호 //todo 확인 필요
        parameters.add("partner_user_id", "partner_user_id");
        parameters.add("item_name", order.getProduct_name());
        parameters.add("quantity", "1");
        parameters.add("total_amount",order.getPrice().toString());
        parameters.add("tax_free_amount", "0");
        parameters.add("approval_url", "http://localhost:8080/sMember/pay/completed"); // 결제승인시 넘어갈 url
        parameters.add("cancel_url", "http://localhost:8080/sMember/pay/cancel"); // 결제취소시 넘어갈 url
        parameters.add("fail_url", "http://localhost:8080/sMember/pay/fail"); // 결제 실패시 넘어갈 url

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
        // 외부url요청 통로 열기.
        RestTemplate template = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/ready";
        // template으로 값을 보내고 받아온 ReadyResponse값 readyResponse에 저장.

        RequestPayDto readyResponse = template.postForObject(url, requestEntity, RequestPayDto.class);
        log.info("결재준비 응답객체: " + readyResponse);
        // 받아온 값 return
        return readyResponse;
    }

    // 결제 승인요청 메서드
    public ApproveRequestPayDto payApprove(String tid, String pgToken) {


        // request값 담기.
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", "TC0ONETIME"); //가맹점 코드 10자
        parameters.add("tid", tid); //결제 고유번호
        parameters.add("partner_order_id", "partner_order_id"); // 가맹점 주문번호, 결제 준비 api요청과 일치 필요
        parameters.add("partner_user_id", "partner_user_id" ); //가맹점 회원 id, 결제 준비 api 요청과 일치 order.getSMember_id()
        parameters.add("pg_token", pgToken);

        // 하나의 map안에 header와 parameter값을 담아줌.
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // 외부url 통신
        RestTemplate template = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/approve";
        // 보낼 외부 url, 요청 메시지(header,parameter), 처리후 값을 받아올 클래스.
        ApproveRequestPayDto approveResponse = template.postForObject(url, requestEntity, ApproveRequestPayDto.class);

        return approveResponse;
    }
    // header() 셋팅
    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","KakaoAK 942dc22415c337900d9f5159a808d612" );
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return headers;
    }
}
