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
public class SMember {
    @Id
    @Column(length = 100, nullable = false)
    private String sMember_id;

    @JsonIgnore
    @Column(length = 100, nullable = false)
    private String sMember_pwd;

    @Column(length = 100, nullable = false)
    private String sMember_nickname;

    @Column(length = 100, nullable = false)
    private String sMember_phoneNumber;

    @OneToMany(mappedBy = "sMember")
    private List<Spon> spons = new ArrayList<>();

}
