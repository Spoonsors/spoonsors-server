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
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //MySQL의 AUTO_INCREMENT를 사용
    @Column(nullable = false)
    private Long review_id;

    @OneToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Lob
    private byte[] review_img;

    @Column(length = 400)
    private String review_txt;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date review_date;
}
