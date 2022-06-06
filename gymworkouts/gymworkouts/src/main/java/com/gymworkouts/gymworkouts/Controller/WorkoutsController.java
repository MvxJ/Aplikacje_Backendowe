package com.gymworkouts.gymworkouts.Controller;

import com.gymworkouts.gymworkouts.Entity.WorkoutEntity;
import com.gymworkouts.gymworkouts.Repository.WorkoutsRepository;
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

    @RequestMapping(
            value = "/workout/add",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity createWorkout(
            @RequestBody WorkoutEntity workout
    ) {
        try {
            this.workoutsRepository.save(workout);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"result\":\"true\"}");
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"result\":\"false\"}");
        }
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
    public ResponseEntity updateWorkout(
            @PathVariable long id,
            @RequestBody WorkoutEntity workout
    ) {
        Optional<WorkoutEntity> foundWorkoutOptional = this.workoutsRepository.findById(id);

        if (foundWorkoutOptional.isPresent()) {
            WorkoutEntity foundWorkoutEntity = foundWorkoutOptional.get();
            foundWorkoutEntity.setDescription(workout.getDescription());
            foundWorkoutEntity.setName(workout.getName());
            foundWorkoutEntity.setSex(workout.getSex());
            foundWorkoutEntity.setRecommendedWeight(workout.getRecommendedWeight());
            foundWorkoutEntity.setRecommendedRepetitions(workout.getRecommendedRepetitions());

            this.workoutsRepository.save(foundWorkoutEntity);
        }

        return ResponseEntity.of(foundWorkoutOptional);
    }

    public ResponseEntity searchWokout() {

    }
}
