package com.gymworkouts.gymworkouts.Service;

import com.gymworkouts.gymworkouts.Entity.CategoryEntity;
import com.gymworkouts.gymworkouts.Entity.ImageEntity;
import com.gymworkouts.gymworkouts.Entity.WorkoutEntity;
import com.gymworkouts.gymworkouts.Repository.CategoryRepository;
import com.gymworkouts.gymworkouts.Repository.WorkoutsRepository;
import com.gymworkouts.gymworkouts.Requests.CreateWorkoutEntityRequest;
import com.gymworkouts.gymworkouts.Requests.UpdateWorkoutEntityRequest;
import com.gymworkouts.gymworkouts.Responses.CreateResponse;
import com.gymworkouts.gymworkouts.Responses.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WorkoutService {
    @Autowired
    WorkoutsRepository workoutsRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public UpdateResponse updateWorkout(WorkoutEntity workoutEntity, UpdateWorkoutEntityRequest updateRequest) {
        try {
            if (updateRequest.getWorkoutName() != null) {
                workoutEntity.setName(updateRequest.getWorkoutName());
            }

            workoutEntity.setDescription(updateRequest.getDescription());
            workoutEntity.setSex(updateRequest.getSex());
            workoutEntity.setRecommendedWeight(updateRequest.getRecomendedWeight());
            workoutEntity.setRecommendedRepetitions(updateRequest.getRecomendedRedemption());

            Optional<CategoryEntity> category = categoryRepository.findById(updateRequest.getCategoryId());

            if (category.isPresent()) {
                CategoryEntity newCategory = category.get();
                workoutEntity.setCategory(newCategory);
            }

            this.workoutsRepository.save(workoutEntity);
            UpdateResponse response = new UpdateResponse(true, "Successfully updated workout entity!");

            return response;
        } catch (Exception exception) {
            UpdateResponse response = new UpdateResponse(false, exception.getMessage());

            return response;
        }
    }

    public CreateResponse createWorkout(CreateWorkoutEntityRequest requestEntity) {

        try {
            WorkoutEntity workout = new WorkoutEntity();
            workout.setName(requestEntity.getWorkoutName());
            workout.setSex(requestEntity.getSex());
            workout.setDescription(requestEntity.getDescription());
            workout.setRecommendedRepetitions(requestEntity.getRecomendedRedemptions());
            workout.setRecommendedWeight(requestEntity.getRecomendedWeight());

            workoutsRepository.save(workout);

            return new CreateResponse(true, "Successfully created workout entity!", workout);
        } catch (Exception exception){
            return new CreateResponse(false, exception.getMessage(), null);
        }
    }
}
