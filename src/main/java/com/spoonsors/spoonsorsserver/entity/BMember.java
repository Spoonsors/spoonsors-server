package com.spoonsors.spoonsorsserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BMember {
    @Id
    @Column(length = 100, nullable = false)
    private String bMember_id;

    @JsonIgnore
    @Column( length = 100, nullable = false)
    private String bMember_pwd;

    @Column( length = 100, nullable = false)
    private String bMember_nickname;

    @Column( length = 100, nullable = false)
    private String bMember_phoneNumber;

    @Column( length = 100, nullable = false)
    private String bMember_address;

    @Column(nullable = false) //증명서 이미지
    private byte[] bMember_certificate;

    @OneToMany(mappedBy = "bMember")
    private List<Post> posts = new ArrayList<>();

}
