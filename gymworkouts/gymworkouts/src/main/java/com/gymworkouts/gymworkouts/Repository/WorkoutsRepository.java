package com.gymworkouts.gymworkouts.Repository;

import com.gymworkouts.gymworkouts.Entity.WorkoutEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface WorkoutsRepository extends JpaRepository<WorkoutEntity, Long> {
    Optional<WorkoutEntity> findById(long workoutId);
    List<WorkoutEntity> findByCategoryId(long categoryId, PageRequest pageRequest);
    List<WorkoutEntity> findAll();
//    @Query(value="SELECT FROM workouts WHERE name LIKE '%?1%' OR description LIKE '%?1%'")
//    List<WorkoutEntity> searchByKeyWord(String terms);
}
