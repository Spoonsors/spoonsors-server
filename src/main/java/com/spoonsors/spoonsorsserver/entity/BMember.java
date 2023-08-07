package com.spoonsors.spoonsorsserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BMember extends BaseTime{
    @Id
    @Column(length = 100, nullable = false)
    private String bMember_id;

    @JsonIgnore
    @Column( length = 100, nullable = false)
    private String bMember_pwd;

    @Column( length = 100, nullable = false)
    private String bMember_nickname;

    @JsonIgnore
    @Column( length = 100, nullable = false)
    private String bMember_phoneNumber;

    @JsonIgnore
    @Column( length = 100, nullable = false)
    private String bMember_address;

    @JsonIgnore
    @Column(nullable = false) //증명서 이미지
    private byte[] bMember_certificate;

    @OneToMany(mappedBy = "bMember")
    private List<Post> posts = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Role role;

    public void encodePassword(PasswordEncoder passwordEncoder){
        this.bMember_pwd = passwordEncoder.encode(bMember_pwd);
    }

}
