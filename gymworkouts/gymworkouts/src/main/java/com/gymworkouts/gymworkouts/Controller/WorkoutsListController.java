package com.gymworkouts.gymworkouts.Controller;

import com.gymworkouts.gymworkouts.Entity.WorkoutListEntity;
import com.gymworkouts.gymworkouts.Repository.WorkoutsListRepository;
import com.gymworkouts.gymworkouts.Repository.WorkoutsRepository;
import com.gymworkouts.gymworkouts.Requests.CreateWorkoutListRequest;
import com.gymworkouts.gymworkouts.Requests.UpdateWorkoutListRequest;
import com.gymworkouts.gymworkouts.Responses.CreateResponse;
import com.gymworkouts.gymworkouts.Responses.DeleteResponse;
import com.gymworkouts.gymworkouts.Responses.UpdateResponse;
import com.gymworkouts.gymworkouts.Responses.WorkoutListActionResponse;
import com.gymworkouts.gymworkouts.Service.AuthService;
import com.gymworkouts.gymworkouts.Service.WorkoutsListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class WorkoutsListController {
    @Autowired
    private WorkoutsListRepository workoutListsRepository;

    @Autowired
    private WorkoutsRepository workoutsRepository;

    @Autowired
    private WorkoutsListService workoutsListService;

    @Autowired
    private AuthService authService;

    @RequestMapping(
            value = "/user/workouts/list/create",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CreateResponse> addWorkoutsList(
            HttpServletRequest request,
            @RequestBody CreateWorkoutListRequest workoutEntityRequest
    ) {
        try {
            return this.workoutsListService.createList(request, workoutEntityRequest);
        } catch (Exception exception){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CreateResponse(false, exception.getMessage(), null));
        }
    }

    @RequestMapping(
            value = "/user/workouts/list/add/{workoutId}/{listId}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WorkoutListActionResponse> addWorkoutToList(
            HttpServletRequest request,
            @RequestParam long workoutId,
            @RequestParam long listId
    ) {
        try {
            return this.workoutsListService.addWorkoutToList(workoutId, listId, request);
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new WorkoutListActionResponse(false, exception.getMessage()));
        }
    }

    @RequestMapping(
            value = "/user/workouts/list/remove/{workoutId}/{listId}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WorkoutListActionResponse> removeWorkoutFromList(
            HttpServletRequest request,
            @RequestParam long workoutId,
            @RequestParam long listId
    ) {
        try {
            return this.workoutsListService.removeWorkoutFromList(workoutId, listId, request);
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new WorkoutListActionResponse(false, exception.getMessage()));
        }
    }

    @RequestMapping(
            value = "/user/workouts/list/delete/{listId}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<DeleteResponse> removeWorkoutList(
            HttpServletRequest request,
            @RequestParam long listId
    ) {
        try {
            return this.workoutsListService.deleteList(listId, request);
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new DeleteResponse(false, exception.getMessage()));
        }
    }

    @RequestMapping(
            value = "/user/workouts/list/update/{listId}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UpdateResponse> updateWorkoutList(
            HttpServletRequest request,
            @RequestParam long listId,
            @RequestBody UpdateWorkoutListRequest updateRequest
    ) {
        try {
            return this.workoutsListService.updateList(listId, updateRequest, request);
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new UpdateResponse(false, exception.getMessage()));
        }
    }

    @RequestMapping(
            value = "/user/workouts/list/{listId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WorkoutListEntity> getWorkoutList(
            HttpServletRequest request,
            @RequestParam long listId
    ) {
        if (this.authService.isUserLogged(request.getSession())) {
            return ResponseEntity.of(this.workoutListsRepository.findById(listId));
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
    }

    @RequestMapping(
            value = "/user/workouts/lists",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<WorkoutListEntity>> getUserWorkoutLists(
            HttpServletRequest request
    ) {
        if (this.authService.isUserLogged(request.getSession())) {
            long userId = this.authService.getLoggedUserId(request.getSession());

            return ResponseEntity.ok(this.workoutListsRepository.findAllByUserId(userId));
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
    }
}
