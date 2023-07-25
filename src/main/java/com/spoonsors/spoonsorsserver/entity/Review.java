package com.spoonsors.spoonsorsserver.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {
    @Id
    @Column(nullable = false)
    private Long review_id;

    @OneToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(nullable = false)
    private byte[] review_img;

    @Column(length = 400)
    private String review_txt;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date review_date;
}
