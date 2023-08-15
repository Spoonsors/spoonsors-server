package com.spoonsors.spoonsorsserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name="bmember",
        uniqueConstraints={
                @UniqueConstraint(
                        name="phoneAndName",
                        columnNames={"bMember_name", "bMember_phoneNumber"}
                )
        }
)
public class BMember extends BaseTime{
    @Id
    @Column(length = 100, nullable = false)
    private String bMember_id;

    @JsonIgnore
    @Column( length = 100, nullable = false)
    private String bMember_pwd;

    @Column( length = 100, nullable = false,unique=true)
    private String bMember_nickname;

    @Column( length = 100, nullable = false)
    private String bMember_name;

    @JsonIgnore
    @Column( length = 100, nullable = false, unique=true)
    private String bMember_phoneNumber;

    @JsonIgnore
    @Column( length = 100, nullable = false)
    private String bMember_address;

    @JsonIgnore
    @Column(nullable = false) //증명서 이미지
    private byte[] bMember_certificate;

    @JsonIgnore
    @OneToMany(mappedBy = "bMember")
    private List<Post> posts = new ArrayList<>();

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private Role role;

    private String token;

    @ColumnDefault("0")
    private int is_verified; //초기 0, 증명 완료 1

    public void encodePassword(PasswordEncoder passwordEncoder){
        this.bMember_pwd = passwordEncoder.encode(bMember_pwd);
    }

}
