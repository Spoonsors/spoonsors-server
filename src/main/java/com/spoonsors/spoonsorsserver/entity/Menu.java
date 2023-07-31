//package com.spoonsors.spoonsorsserver.entity;
//
//import lombok.*;
//import javax.persistence.*;
//
//
//@ToString
//@Getter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//public class Menu {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO) //MySQL의 AUTO_INCREMENT를 사용
//    @Column(nullable = false)
//    private Long menu_id;
//
//    @Column( nullable = false)
//    private Long menu_name;
//
//    @Column( nullable = false) //mediumblob
//    private byte[] menu_img;
//
//    private Integer kcal;
//
//    private Integer carbohydrate;
//
//    private Integer protein;
//
//    private Integer fat;
//
//    @Column(length = 2048)
//    private String url;
//
//}
