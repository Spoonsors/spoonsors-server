package com.spoonsors.spoonsorsserver.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 AUTO_INCREMENT를 사용
    @Column(nullable = false)
    private Long post_id;

    @ManyToOne
    @JoinColumn(name = "writer_id", nullable = false)
    private BMember bMember;

    @Column( length = 100)
    private String post_title;

    @Column(length = 400)
    private String post_txt;

    @ColumnDefault("0")
    private Integer post_state;

    @ColumnDefault("0") //리뷰 없으면 0, 있으면 1
    private Integer has_review;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column( nullable = false) //datetime
    private Date post_date;

    @OneToMany(mappedBy = "post")
    private List<Spon> spon = new ArrayList<>();


}
