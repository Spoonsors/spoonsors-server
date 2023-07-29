package com.spoonsors.spoonsorsserver.entity.manager;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetManagerDto {
    private Long ingredients_id;
    private String ingredients_name;
    private String product_name;
    private byte[] ingredients_image;
    private Integer price;

    public GetManagerDto(Long ingredients_id, String ingredients_name,
                         String product_name, byte[] ingredients_image, Integer price){
        this.ingredients_id = ingredients_id;
        this.ingredients_name = ingredients_name;
        this.product_name = product_name;
        this.ingredients_image = ingredients_image;
        this.price = price;
    }
}
