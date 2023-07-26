package com.spoonsors.spoonsorsserver.entity;

import lombok.*;

import javax.persistence.*;
        import java.awt.*;
        import java.sql.Time;
import java.util.ArrayList;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MealPlanner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 AUTO_INCREMENT를 사용
    private Long mealPlanner_id;

    @ManyToMany
    @JoinTable(name = "MealPlanner_Menu",
            joinColumns = @JoinColumn(name = "mealPlanner_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id"))
    private List<Menu> menu = new ArrayList<>();

    @Column(nullable = false)
    private String mealPlanner_name;
    private String level;
    private Time timeRequired;
}
