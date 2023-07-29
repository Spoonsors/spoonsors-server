package com.spoonsors.spoonsorsserver.entity;
import lombok.*;

import javax.persistence.*;
import javax.swing.*;
import java.awt.*;
import java.sql.Time;

@ToString
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ingredients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 AUTO_INCREMENT를 사용
    private Long ingredients_id;

    @Column(length = 100, nullable = false)
    private String ingredients_name;

    @Column(length = 100)
    private String product_name;

    @Column(nullable = false)
    private byte[] ingredients_image;

    @Column(nullable = false)
    private Integer price;
}
