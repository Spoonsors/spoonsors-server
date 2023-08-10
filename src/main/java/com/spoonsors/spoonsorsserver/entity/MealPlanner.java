package com.spoonsors.spoonsorsserver.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@ToString
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MealPlanner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //MySQL의 AUTO_INCREMENT를 사용
    private Long mealPlanner_id;

//    @ManyToMany
//    @JoinTable(name = "MealPlanner_Menu",
//            joinColumns = @JoinColumn(name = "mealPlanner_id"),
//            inverseJoinColumns = @JoinColumn(name = "menu_id"))
//    private List<Menu> menu = new ArrayList<>();

    @Column( nullable = false)
    private String menu_name1;
    @Column( nullable = false) //mediumblob
    private String menu_img1;

    private String menu_name2;
    private String menu_img2;

    private String menu_name3;
    private String menu_img3;


    private String menu_name4;
    private String menu_img4;

    @Column(length = 100, nullable = false)
    private String mealPlanner_name;

    private Float kcal;

    private Float carbohydrate;

    private Float protein;

    private Float fat;
    private Float na;
    @Column(length = 10)
    private String level;

    @Temporal(value = TemporalType.TIME)
    private Date timeRequired;
}
