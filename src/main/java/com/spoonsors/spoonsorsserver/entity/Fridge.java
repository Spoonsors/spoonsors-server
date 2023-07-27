package com.spoonsors.spoonsorsserver.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Fridge {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //MySQL의 AUTO_INCREMENT를 사용
    @Column(nullable = false)
    private Long fridge_id;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId  //@Id와 연결시켜 기본키이자 외래키로 사용
    @JoinColumn(name = "member_id")
    private BMember bMember;

    @Column( length = 100, nullable = false)
    private String fridge_item_name;

    private byte[] fridge_item_img;

    @Temporal(value = TemporalType.DATE)
    private Date expiration_date;
}
