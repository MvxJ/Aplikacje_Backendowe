package com.gymworkouts.gymworkouts.Interfaces;

import com.gymworkouts.gymworkouts.Entity.WorkoutEntity;

import java.util.List;

public interface CustomRepositoryInterface {
    List<WorkoutEntity> findWorkoutByNameOrDescription(String keyWord, String keyWord2);
}
