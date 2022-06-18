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

import java.util.List;
import java.util.Optional;

public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private WorkoutsRepository workoutsRepository;

    public UpdateResponse updateCategory(CategoryEntity categoryEntity, UpdateCategoryRequest request) {
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

            return new UpdateResponse(true, "Successfully updated category!");
        } catch (Exception exception){
            return new UpdateResponse(false, exception.getMessage());
        }
    }

    public CreateResponse createCategory(CreateCategoryRequest request) {
        try {
            CategoryEntity category = new CategoryEntity();
            category.setName(request.getCategoryName());
            category.setDescription(request.getDescription());

            this.categoryRepository.save(category);

            return new CreateResponse(true, "Successfully created category!", category);
        } catch (Exception exception) {
            return new CreateResponse(false, exception.getMessage(), null);
        }
    }

    public DeleteResponse deleteCategory(Long categoryId) {
        Optional<CategoryEntity> categoryEntity = this.categoryRepository.findById(categoryId);

        if (categoryEntity.isPresent()) {
            try {
                this.categoryRepository.deleteById(categoryId);

                return new DeleteResponse(true, "Successfully deleted category");
            } catch (Exception exception) {
                return new DeleteResponse(false, exception.getMessage());
            }
        }

        return new DeleteResponse(false, "Entity not found!");
    }
}
