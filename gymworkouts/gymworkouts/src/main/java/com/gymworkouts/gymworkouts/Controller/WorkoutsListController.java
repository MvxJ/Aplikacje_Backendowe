package com.gymworkouts.gymworkouts.Controller;

import com.gymworkouts.gymworkouts.Entity.WorkoutListEntity;
import com.gymworkouts.gymworkouts.Repository.WorkoutsListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkoutsListController {
    @Autowired
    WorkoutsListRepository workoutListsRepository;

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

    public ResponseEntity addWorkoutToList() {

    }

    public ResponseEntity removeWorkoutFromList() {

    }

    public ResponseEntity removeWorkoutList() {

    }

    public ResponseEntity updateWorkoutEntity() {

    }
}
