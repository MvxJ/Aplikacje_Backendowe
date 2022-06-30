package com.gymworkouts.gymworkouts.Repository;

import com.gymworkouts.gymworkouts.Entity.WorkoutEntity;
import com.gymworkouts.gymworkouts.Interfaces.CustomRepositoryInterface;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkoutsRepository extends JpaRepository<WorkoutEntity, Long>, CustomRepositoryInterface {
    Optional<WorkoutEntity> findById(long workoutId);
    List<WorkoutEntity> findByCategoryId(long categoryId, PageRequest pageRequest);
    List<WorkoutEntity> findAll();
    List<WorkoutEntity> findWorkoutByNameOrDescription(String nameKeyWord, String descriptionKeyWord);
    List<WorkoutEntity> findWorkoutByNameIsContainingOrDescriptionIsContaining(String nameKeyWord, String descriptionKeyWord);
}
