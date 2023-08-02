package com.spoonsors.spoonsorsserver.repository;

import com.spoonsors.spoonsorsserver.entity.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IIngredientsRepository extends JpaRepository<Ingredients, Long> {
}
