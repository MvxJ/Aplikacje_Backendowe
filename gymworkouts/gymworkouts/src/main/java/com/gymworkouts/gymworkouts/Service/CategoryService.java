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

            if (request.getAddWorkoutId() != null) {
                List<WorkoutEntity> workouts = categoryEntity.getWorkouts();
                Optional<WorkoutEntity> workout = this.workoutsRepository.findById(request.getAddWorkoutId());

                if (workout.isPresent()) {
                    workouts.add(workout.get());
                }

                categoryEntity.setWorkouts(workouts);
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
}
