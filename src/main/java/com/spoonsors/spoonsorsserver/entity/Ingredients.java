package com.spoonsors.spoonsorsserver.entity;
import lombok.*;

import javax.persistence.*;
import javax.swing.*;
import java.awt.*;
import java.sql.Time;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ingredients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 AUTO_INCREMENT를 사용
    private Long ingredients_id;

    @Column(nullable = false)
    private String ingredients_name;

    private String product_name;
    @Column(nullable = false)
    private byte[] ingredients_image;
    @Column(nullable = false)
    private int price;
}
