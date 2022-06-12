package com.gymworkouts.gymworkouts.Repository;

import com.gymworkouts.gymworkouts.Entity.WorkoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkoutsRepository extends JpaRepository<WorkoutEntity, Long> {
    Optional<WorkoutEntity> findById(long workoutId);
    List<WorkoutEntity> findByCategoryId(long categoryId);
    List<WorkoutEntity> findAll();
    List<WorkoutEntity> findByNameLike(String terms);
}
