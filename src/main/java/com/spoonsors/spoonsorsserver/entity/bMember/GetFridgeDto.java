package com.spoonsors.spoonsorsserver.entity.bMember;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class GetFridgeDto {
    private String ownerId;
    private String name;
    private Integer isFrized;
    private byte[] img;
    private Date expirationDate;

    public GetFridgeDto(String ownerId, String name, Integer isFrized,
                        byte[] img, Date expirationDate){
        this.expirationDate=expirationDate;
        this.img=img;
        this.isFrized=isFrized;
        this.ownerId=ownerId;
        this.name=name;
    }
}
