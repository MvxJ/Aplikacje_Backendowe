package com.gymworkouts.gymworkouts.Service;

import com.gymworkouts.gymworkouts.Entity.CategoryEntity;
import com.gymworkouts.gymworkouts.Entity.WorkoutEntity;
import com.gymworkouts.gymworkouts.Repository.CategoryRepository;
import com.gymworkouts.gymworkouts.Repository.WorkoutsRepository;
import com.gymworkouts.gymworkouts.Requests.CreateCategoryRequest;
import com.gymworkouts.gymworkouts.Requests.UpdateCategoryRequest;
import com.gymworkouts.gymworkouts.Responses.CreateResponse;
import com.gymworkouts.gymworkouts.Responses.DeleteResponse;
import com.gymworkouts.gymworkouts.Responses.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private WorkoutsRepository workoutsRepository;

    public ResponseEntity<UpdateResponse> updateCategory(CategoryEntity categoryEntity, UpdateCategoryRequest request) {
        try {
            if (request.getCategoryName() != null) {
                categoryEntity.setName(request.getCategoryName());
            }

            if (request.getDescription() != null) {
                 categoryEntity.setDescription(request.getDescription());
            }

            this.categoryRepository.save(categoryEntity);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new UpdateResponse(true, "Successfully updated category!"));
        } catch (Exception exception){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new UpdateResponse(false, exception.getMessage()));
        }
    }

    public ResponseEntity<CreateResponse> createCategory(CreateCategoryRequest request) {
        try {
            CategoryEntity category = new CategoryEntity();
            category.setName(request.getCategoryName());
            category.setDescription(request.getDescription());

            this.categoryRepository.save(category);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new CreateResponse(true, "Successfully created category!", category));
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CreateResponse(false, exception.getMessage(), null));
        }
    }

    public ResponseEntity<DeleteResponse> deleteCategory(Long categoryId) {
        Optional<CategoryEntity> categoryEntity = this.categoryRepository.findById(categoryId);

        if (categoryEntity.isPresent()) {
            try {
                this.categoryRepository.deleteById(categoryId);

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new DeleteResponse(true, "Successfully deleted category"));
            } catch (Exception exception) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new DeleteResponse(false, exception.getMessage()));
            }
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new DeleteResponse(false, "Entity not found!"));
    }

    public ResponseEntity<UpdateResponse> addWorkoutToCategory(long categoryId, long workoutId) {
        try {
            if (categoryId <= 0 && workoutId <= 0) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new UpdateResponse(false, "Bad request!"));
            }

            Optional<CategoryEntity> category = this.categoryRepository.findById(categoryId);
            Optional<WorkoutEntity> workout = this.workoutsRepository.findById(workoutId);

            if (workout.isPresent() && category.isPresent()) {
                CategoryEntity categoryEntity = category.get();
                categoryEntity.assignWorkout(workout.get());
                WorkoutEntity workoutEntity = workout.get();
                workoutEntity.setCategoryId(categoryEntity.getId());

                this.workoutsRepository.save(workoutEntity);
                this.categoryRepository.save(categoryEntity);

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new UpdateResponse(true, "Successfully added workout to category!"));
            }

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new UpdateResponse(false, "Entity not found!"));
        } catch(Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new UpdateResponse(false, exception.getMessage()));
        }
    }

    public ResponseEntity<UpdateResponse> removeWorkout(long categoryId, long workoutId) {
        try {
            if (categoryId >= 0 && workoutId >= 0) {
                Optional<CategoryEntity> optionalCategory = this.categoryRepository.findById(categoryId);
                Optional<WorkoutEntity> optionalWorkout = this.workoutsRepository.findById(workoutId);

                if (optionalCategory.isPresent() && optionalWorkout.isPresent()) {
                    CategoryEntity category = optionalCategory.get();
                    category.removeWorkout(optionalWorkout.get());
                    WorkoutEntity workoutEntity = optionalWorkout.get();
                    workoutEntity.setCategoryId(0);

                    this.workoutsRepository.save(workoutEntity);
                    this.categoryRepository.save(category);

                    return ResponseEntity
                            .status(HttpStatus.OK)
                            .body(new UpdateResponse(true, "Successfully removed workout from category!"));
                }

                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new UpdateResponse(false, "Entity not found!"));
            }

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new UpdateResponse(false, "Bad request!"));
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new UpdateResponse(false, exception.getMessage()));
        }
    }
}
