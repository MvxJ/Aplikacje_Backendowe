package com.gymworkouts.gymworkouts.Repository;

import com.gymworkouts.gymworkouts.Entity.WorkoutListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkoutsListRepository extends JpaRepository<WorkoutListEntity, Long> {
    Optional<WorkoutListEntity> findById(long workoutListId);
    List<WorkoutListEntity> findAllByUserId(long userId);
}
