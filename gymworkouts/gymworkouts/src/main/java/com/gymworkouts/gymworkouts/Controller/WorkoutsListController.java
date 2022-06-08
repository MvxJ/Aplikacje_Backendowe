package com.gymworkouts.gymworkouts.Controller;

import com.gymworkouts.gymworkouts.Entity.WorkoutEntity;
import com.gymworkouts.gymworkouts.Entity.WorkoutListEntity;
import com.gymworkouts.gymworkouts.Repository.WorkoutsListRepository;
import com.gymworkouts.gymworkouts.Repository.WorkoutsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class WorkoutsListController {
    @Autowired
    WorkoutsListRepository workoutListsRepository;

    @Autowired
    WorkoutsRepository workoutsRepository;

    @RequestMapping(
            value = "/user/workouts/list/create",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity addWorkoutsList(
            @RequestBody WorkoutListEntity workoutList
    ) {
        try {
            this.workoutListsRepository.save(workoutList);

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
            value = "/user/workouts/list/add/{workoutId}/{listId}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity addWorkoutToList(
            @RequestParam long workoutId,
            @RequestParam long listId
    ) {
        Optional<WorkoutEntity> workout = this.workoutsRepository.findById(workoutId);
        Optional<WorkoutListEntity> list = this.workoutListsRepository.findById(listId);

        if (list.isPresent() && workout.isPresent()) {
            try {
                WorkoutEntity workoutEntity = workout.get();
                WorkoutListEntity workoutList = list.get();
                List<WorkoutEntity> workouts = workoutList.getWorkouts();
                workouts.add(workoutEntity);
                workoutList.setWorkouts(workouts);

                this.workoutListsRepository.save(workoutList);
            } catch (Exception exception) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"error\":\"" + exception.getMessage() + "\"}");
            }
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"error\":\"Entity not found!\"}");
    }

    @RequestMapping(
            value = "/user/workouts/list/remove/{workoutId}/{listId}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity removeWorkoutFromList(
            @RequestParam long workoutId,
            @RequestParam long listId
    ) {
        Optional<WorkoutEntity> workout = this.workoutsRepository.findById(workoutId);
        Optional<WorkoutListEntity> list = this.workoutListsRepository.findById(listId);

        if (list.isPresent() && workout.isPresent()) {
            try {
                WorkoutEntity workoutEntity = workout.get();
                WorkoutListEntity workoutList = list.get();
                List<WorkoutEntity> workouts = workoutList.getWorkouts();
                workouts.remove(workoutEntity);
                workoutList.setWorkouts(workouts);

                this.workoutListsRepository.save(workoutList);
            } catch (Exception exception) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"error\":\"" + exception.getMessage() + "\"}");
            }
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"error\":\"Entity not found!\"}");
    }

    @RequestMapping(
            value = "/user/workouts/list/delete/{listId}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity removeWorkoutList(
            @RequestParam long listId
    ) {
        Optional<WorkoutListEntity> foundEntity = this.workoutListsRepository.findById(listId);

        if (foundEntity.isPresent()) {
            try {
                this.workoutListsRepository.deleteById(listId);

                return ResponseEntity.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"result\":\"true\"}");
            } catch (Exception exception) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"error\":\"" + exception.getMessage() + "\"}");
            }
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"error\":\"Entity not found!\"}");
    }

    @RequestMapping(
            value = "/user/workouts/list/update/{listId}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity updateWorkoutList(
            @RequestParam long listId,
            @RequestBody WorkoutListEntity workoutList
    ) {
        Optional<WorkoutListEntity> foundListEntity = this.workoutListsRepository.findById(listId);

        if (foundListEntity.isPresent()) {
            WorkoutListEntity foundEntity = foundListEntity.get();
            foundEntity.setDescription(workoutList.getDescription());
            foundEntity.setName(workoutList.getName());

            this.workoutListsRepository.save(foundEntity);
        }

        return ResponseEntity.of(foundListEntity);
    }
}
