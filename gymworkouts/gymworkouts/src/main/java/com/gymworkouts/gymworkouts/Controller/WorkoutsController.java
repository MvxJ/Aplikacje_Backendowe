package com.gymworkouts.gymworkouts.Controller;

import com.gymworkouts.gymworkouts.Entity.WorkoutEntity;
import com.gymworkouts.gymworkouts.Repository.CategoryRepository;
import com.gymworkouts.gymworkouts.Repository.WorkoutsRepository;
import com.gymworkouts.gymworkouts.Requests.CreateWorkoutEntityRequest;
import com.gymworkouts.gymworkouts.Requests.UpdateWorkoutEntityRequest;
import com.gymworkouts.gymworkouts.Responses.CreateResponse;
import com.gymworkouts.gymworkouts.Responses.UpdateResponse;
import com.gymworkouts.gymworkouts.Service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class WorkoutsController {
    @Autowired
    private WorkoutsRepository workoutsRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private WorkoutService workoutService;

    @RequestMapping(
            value = "/workout/add",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CreateResponse createWorkout(
            @RequestBody CreateWorkoutEntityRequest request
    ) {
        return this.workoutService.createWorkout(request);
    }

    @RequestMapping(
            value = "/workout",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<WorkoutEntity>> getWorkouts() {
        return ResponseEntity.ok(this.workoutsRepository.findAll());
    }

    @RequestMapping(
            value = "/workout/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WorkoutEntity> getWorkout(
            @PathVariable long id
    ) {
        return ResponseEntity.of(this.workoutsRepository.findById(id));
    }

    @RequestMapping(
            value = "/workout/delete/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity deleteWorkout(
            @PathVariable long id
    ) {
        Optional<WorkoutEntity> foundWorkoutOptional = this.workoutsRepository.findById(id);

        if (foundWorkoutOptional.isPresent()) {
            this.workoutsRepository.deleteById(id);
        }

        return ResponseEntity.of(foundWorkoutOptional);
    }

    @RequestMapping(
            value = "/workout/update/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public UpdateResponse updateWorkout(
            @PathVariable long id,
            @RequestBody UpdateWorkoutEntityRequest updateWorkoutEntity
    ) {
        Optional<WorkoutEntity> foundWorkoutOptional = this.workoutsRepository.findById(id);

        if (foundWorkoutOptional.isPresent()) {
            return this.workoutService.updateWorkout(foundWorkoutOptional.get(), updateWorkoutEntity);
        }

        return new UpdateResponse(false, "Workout entity not found!");
    }

    @RequestMapping(
            value = "/workout/search",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<WorkoutEntity>> searchWokout(
            @RequestBody String searchText
    ) {
        return ResponseEntity.ok(this.workoutsRepository.findByNameLike(searchText));
    }

    @RequestMapping(
            value = "/workout/category/{categoryId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<WorkoutEntity>> getWorkoutsByCategory(
            @RequestParam long categoryId
    ) {
        return ResponseEntity.ok(this.workoutsRepository.findByCategoryId(categoryId));
    }
}
