package com.spoonsors.spoonsorsserver.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@ToString
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SMember extends BaseTime {
    @Id
    @Column(length = 100, nullable = false)
    private String sMember_id;

    @JsonIgnore
    @Column(length = 100, nullable = false)
    private String sMember_pwd;

    @Column(length = 100, nullable = false)
    private String sMember_nickname;

    @JsonIgnore
    @Column(length = 100, nullable = false)
    private String sMember_phoneNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "sMember")
    private List<Spon> spons = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Role role;

    public void encodePassword(PasswordEncoder passwordEncoder){
        this.sMember_pwd = passwordEncoder.encode(sMember_pwd);
    }
}
