package com.gymworkouts.gymworkouts.Controller;

import com.gymworkouts.gymworkouts.Entity.WorkoutEntity;
import com.gymworkouts.gymworkouts.Repository.WorkoutsRepository;
import com.gymworkouts.gymworkouts.Requests.CreateWorkoutEntityRequest;
import com.gymworkouts.gymworkouts.Requests.UpdateWorkoutEntityRequest;
import com.gymworkouts.gymworkouts.Responses.CreateResponse;
import com.gymworkouts.gymworkouts.Responses.DeleteResponse;
import com.gymworkouts.gymworkouts.Responses.UpdateResponse;
import com.gymworkouts.gymworkouts.Service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private WorkoutService workoutService;

    @RequestMapping(
            value = "/workout/add",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CreateResponse> createWorkout(
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
    public ResponseEntity<DeleteResponse> deleteWorkout(
            @PathVariable long id
    ) {
        return this.workoutService.deleteWorkout(id);
    }

    @RequestMapping(
            value = "/workout/update/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UpdateResponse> updateWorkout(
            @PathVariable long id,
            @RequestBody UpdateWorkoutEntityRequest updateWorkoutEntity
    ) {
        Optional<WorkoutEntity> foundWorkoutOptional = this.workoutsRepository.findById(id);

        if (foundWorkoutOptional.isPresent()) {
            return this.workoutService.updateWorkout(foundWorkoutOptional.get(), updateWorkoutEntity);
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new UpdateResponse(false, "Workout entity not found!"));
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
            value = "/workout/category/{categoryId}/{page}/{size}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<WorkoutEntity>> getWorkoutsByCategory(
            @RequestParam long categoryId,
            @RequestParam(required = false, defaultValue = "1") int pageNumber,
            @RequestParam(required = false, defaultValue = "32") int size
    ) {
        return ResponseEntity.ok(this.workoutsRepository.findByCategoryId(categoryId));
    }
}
