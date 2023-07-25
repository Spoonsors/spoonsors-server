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
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 AUTO_INCREMENT를 사용
    @Column(nullable = false)
    private Long post_id;

    @ManyToOne
    @JoinColumn(name = "writer_id", nullable = false)
    private BMember bMember;

    @Column( nullable = false) //mediumblob
    private byte[] post_img;

    @Column(length = 400)
    private String post_txt;

    @ColumnDefault("0")
    private Integer post_state;

    @Column( nullable = false) //datetime
    private Date post_date;

    //@OneToMany(mappedBy = "post") //todo spon에 post_id 추가하고 연결하기
    //private List<Spon> spon = new ArrayList<>();


}
