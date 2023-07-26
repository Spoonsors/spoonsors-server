package com.spoonsors.spoonsorsserver.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

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

    @Column(length = 100, nullable = false)
    private String mealPlanner_name;

    @Column(length = 10)
    private String level;

    @Temporal(value = TemporalType.TIME)
    private Date timeRequired;
}
