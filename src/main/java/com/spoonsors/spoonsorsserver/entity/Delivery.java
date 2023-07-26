package com.spoonsors.spoonsorsserver.entity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.awt.*;
import java.sql.Time;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 AUTO_INCREMENT를 사용
    private Long delivery_id;

    @OneToOne // 스폰 하나에 식재료 하나
    @JoinColumn(name = "spon_id",nullable = false)
    private Spon spon;

    @ColumnDefault("0")
    private Integer delivery_state;

}
