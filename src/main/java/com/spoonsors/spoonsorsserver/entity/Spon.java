package com.spoonsors.spoonsorsserver.entity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Spon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 AUTO_INCREMENT를 사용
    private Long spon_id;

    @ManyToOne // 수혜자 하나에 스폰 여러개
    @JoinColumn(name="sMember_id",nullable = false)
    private SMember sMember;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    @OneToOne // 스폰 하나에 식재료 하나
    @JoinColumn(name = "ingredients_id",nullable = false)
    private Ingredients ingredients;

    @Temporal(value = TemporalType.DATE)
    private Date spon_date;

    @ColumnDefault("0")
    private Integer spon_state; // 디폴트 설정
}
