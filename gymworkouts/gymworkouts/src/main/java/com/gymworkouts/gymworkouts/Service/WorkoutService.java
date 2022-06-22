package com.gymworkouts.gymworkouts.Service;

import com.gymworkouts.gymworkouts.Entity.CategoryEntity;
import com.gymworkouts.gymworkouts.Entity.WorkoutEntity;
import com.gymworkouts.gymworkouts.Repository.CategoryRepository;
import com.gymworkouts.gymworkouts.Repository.WorkoutsRepository;
import com.gymworkouts.gymworkouts.Requests.CreateWorkoutEntityRequest;
import com.gymworkouts.gymworkouts.Requests.UpdateWorkoutEntityRequest;
import com.gymworkouts.gymworkouts.Responses.CreateResponse;
import com.gymworkouts.gymworkouts.Responses.DeleteResponse;
import com.gymworkouts.gymworkouts.Responses.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkoutService {
    @Autowired
    private WorkoutsRepository workoutsRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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

            return new UpdateResponse(true, "Successfully updated workout entity!");
        } catch (Exception exception) {
            return new UpdateResponse(false, exception.getMessage());
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

    public DeleteResponse deleteWorkout(Long workoutId) {
        Optional<WorkoutEntity> foundWorkoutOptional = this.workoutsRepository.findById(workoutId);

        if (foundWorkoutOptional.isPresent()) {
            try {
                this.workoutsRepository.deleteById(workoutId);

                return new DeleteResponse(true, "Successfully deleted workout entity!");
            } catch (Exception exception) {
                return new DeleteResponse(false, exception.getMessage());
            }
        }

        return new DeleteResponse(false, "Entity not found");
    }
}
