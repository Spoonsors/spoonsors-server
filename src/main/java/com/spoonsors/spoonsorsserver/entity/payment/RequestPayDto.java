package com.spoonsors.spoonsorsserver.entity.payment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestPayDto {
    private String tid;
    private String next_redirect_pc_url;
    private String partner_order_id;
}
