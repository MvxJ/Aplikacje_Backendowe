package com.gymworkouts.gymworkouts.Repository;

import com.gymworkouts.gymworkouts.Entity.WorkoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WorkoutsRepository extends JpaRepository<WorkoutEntity, Long> {
    Optional<WorkoutEntity> findById(long workoutId);
    List<WorkoutEntity> findAll();
}
