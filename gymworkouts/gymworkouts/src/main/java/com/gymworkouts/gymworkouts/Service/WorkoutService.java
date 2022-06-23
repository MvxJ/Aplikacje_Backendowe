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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkoutService {
    @Autowired
    private WorkoutsRepository workoutsRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseEntity<UpdateResponse> updateWorkout(
            WorkoutEntity workoutEntity,
            UpdateWorkoutEntityRequest updateRequest
    ) {
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

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new UpdateResponse(true, "Successfully updated workout entity!"));
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new UpdateResponse(false, exception.getMessage()));
        }
    }

    public ResponseEntity<CreateResponse> createWorkout(CreateWorkoutEntityRequest requestEntity) {

        try {
            WorkoutEntity workout = new WorkoutEntity();
            workout.setName(requestEntity.getWorkoutName());
            workout.setSex(requestEntity.getSex());
            workout.setDescription(requestEntity.getDescription());
            workout.setRecommendedRepetitions(requestEntity.getRecomendedRedemptions());
            workout.setRecommendedWeight(requestEntity.getRecomendedWeight());

            workoutsRepository.save(workout);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new CreateResponse(true, "Successfully created workout entity!", workout));
        } catch (Exception exception){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CreateResponse(false, exception.getMessage(), null));
        }
    }

    public ResponseEntity<DeleteResponse> deleteWorkout(Long workoutId) {
        Optional<WorkoutEntity> foundWorkoutOptional = this.workoutsRepository.findById(workoutId);

        if (foundWorkoutOptional.isPresent()) {
            try {
                this.workoutsRepository.deleteById(workoutId);

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new DeleteResponse(true, "Successfully deleted workout entity!"));
            } catch (Exception exception) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new DeleteResponse(false, exception.getMessage()));
            }
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new DeleteResponse(false, "Entity not found"));
    }
}
