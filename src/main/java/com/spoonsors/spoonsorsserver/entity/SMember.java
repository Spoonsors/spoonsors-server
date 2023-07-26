package com.spoonsors.spoonsorsserver.entity;
import lombok.*;

import javax.persistence.*;
import java.awt.*;
import java.util.ArrayList;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 AUTO_INCREMENT를 사용
    private String sMember_id;

    @Column(nullable = false)
    private String sMember_pwd;
    @Column(nullable = false)
    private String sMember_nickname;
    @Column(nullable = false)
    private String sMember_phoneNumber;

    @OneToMany(mappedBy = "sMember")
    private List<Spon> spons = new ArrayList<>();
    //@OneToMany(mappedBy = "sMember")
    //private List<Review> reviews = new ArrayList<>();
}
