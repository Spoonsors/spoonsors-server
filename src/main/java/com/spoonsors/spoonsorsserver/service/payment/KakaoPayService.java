package com.spoonsors.spoonsorsserver.service.payment;

import com.spoonsors.spoonsorsserver.entity.payment.ApproveRequestPayDto;
import com.spoonsors.spoonsorsserver.entity.payment.Order;
import com.spoonsors.spoonsorsserver.entity.payment.RequestPayDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;




@Service
public class KakaoPayService {

    private ApproveRequestPayDto approveRequestPayDto;
    private RequestPayDto requestPayDto;

    public String payReady(Order order) {

        // 카카오가 요구한 결제요청request값을 담아줍니다.
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", "TC0ONETIME"); //가맹점 코드. 10자 실제 제휴 후 받아야함
        parameters.add("partner_order_id", "partner_order_id"); // 가맹점의 주문번호
        parameters.add("partner_user_id", "partner_user_id");
        parameters.add("item_name", order.getProduct_name());
        parameters.add("quantity", "1");
        parameters.add("total_amount",order.getPrice().toString());
        parameters.add("tax_free_amount", "0");
        parameters.add("approval_url", "http://localhost:8080/sMember/kakaoPay/completed"); // 결제승인시 넘어갈 url
        parameters.add("cancel_url", "http://localhost:8080/sMember/kakaoPay/cancel"); // 결제취소시 넘어갈 url
        parameters.add("fail_url", "http://localhost:8080/sMember/kakaoPay/fail"); // 결제 실패시 넘어갈 url

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
        // 외부url요청 통로 열기.
        try{
            RestTemplate template = new RestTemplate();
            String url = "https://kapi.kakao.com/v1/payment/ready";

            requestPayDto  = template.postForObject(url, requestEntity, RequestPayDto.class);

            return requestPayDto.getNext_redirect_app_url();

        } catch (RestClientException e) {
            e.printStackTrace();
        }

        return "/pay";
    }

    // 결제 승인요청 메서드
    public ApproveRequestPayDto payApprove( String pgToken) {

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", "TC0ONETIME"); //가맹점 코드. 10자 실제 제휴 후 받아야함
        parameters.add("tid", requestPayDto.getTid()); //결제 고유번호
        parameters.add("partner_order_id", "partner_order_id"); // 가맹점 주문번호, 결제 준비 api요청과 일치 필요
        parameters.add("partner_user_id", "partner_user_id" ); //가맹점 회원 id, 결제 준비 api 요청과 일치
        parameters.add("pg_token", pgToken);

        // 하나의 map안에 header와 parameter값을 담아줌.
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // 외부url 통신
        try {
            RestTemplate template = new RestTemplate();
            String url = "https://kapi.kakao.com/v1/payment/approve";
            // 보낼 외부 url, 요청 메시지(header,parameter), 처리후 값을 받아올 클래스.
            approveRequestPayDto = template.postForObject(url, requestEntity, ApproveRequestPayDto.class);
            return approveRequestPayDto;
        }catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","KakaoAK 942dc22415c337900d9f5159a808d612" );
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return headers;
    }
}
