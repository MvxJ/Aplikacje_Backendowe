package com.gymworkouts.gymworkouts.Controller;

import com.gymworkouts.gymworkouts.Repository.WorkoutsListRepository;
import com.gymworkouts.gymworkouts.Repository.WorkoutsRepository;
import com.gymworkouts.gymworkouts.Requests.CreateWorkoutListRequest;
import com.gymworkouts.gymworkouts.Requests.UpdateWorkoutListRequest;
import com.gymworkouts.gymworkouts.Responses.CreateResponse;
import com.gymworkouts.gymworkouts.Responses.DeleteResponse;
import com.gymworkouts.gymworkouts.Responses.UpdateResponse;
import com.gymworkouts.gymworkouts.Responses.WorkoutListActionResponse;
import com.gymworkouts.gymworkouts.Service.WorkoutsListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class WorkoutsListController {
    @Autowired
    private WorkoutsListRepository workoutListsRepository;

    @Autowired
    private WorkoutsRepository workoutsRepository;

    @Autowired
    private WorkoutsListService workoutsListService;

    @RequestMapping(
            value = "/user/workouts/list/create",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CreateResponse addWorkoutsList(
            HttpServletRequest request,
            @RequestBody CreateWorkoutListRequest workoutEntityRequest
    ) {
        try {
            return this.workoutsListService.createList(request, workoutEntityRequest);
        } catch (Exception exception){
            return new CreateResponse(false, exception.getMessage(), null);
        }
    }

    @RequestMapping(
            value = "/user/workouts/list/add/{workoutId}/{listId}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WorkoutListActionResponse addWorkoutToList(
            HttpServletRequest request,
            @RequestParam long workoutId,
            @RequestParam long listId
    ) {
        try {
            return this.workoutsListService.addWorkoutToList(workoutId, listId, request);
        } catch (Exception exception) {
            return new WorkoutListActionResponse(false, exception.getMessage());
        }
    }

    @RequestMapping(
            value = "/user/workouts/list/remove/{workoutId}/{listId}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WorkoutListActionResponse removeWorkoutFromList(
            HttpServletRequest request,
            @RequestParam long workoutId,
            @RequestParam long listId
    ) {
        try {
            return this.workoutsListService.removeWorkoutFromList(workoutId, listId, request);
        } catch (Exception exception) {
            return new WorkoutListActionResponse(false, exception.getMessage());
        }
    }

    @RequestMapping(
            value = "/user/workouts/list/delete/{listId}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public DeleteResponse removeWorkoutList(
            HttpServletRequest request,
            @RequestParam long listId
    ) {
        try {
            return this.workoutsListService.deleteList(listId, request);
        } catch (Exception exception) {
            return  new DeleteResponse(false, exception.getMessage());
        }
    }

    @RequestMapping(
            value = "/user/workouts/list/update/{listId}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public UpdateResponse updateWorkoutList(
            HttpServletRequest request,
            @RequestParam long listId,
            @RequestBody UpdateWorkoutListRequest updateRequest
    ) {
        try {
            return this.workoutsListService.updateList(listId, updateRequest, request);
        } catch (Exception exception) {
            return new UpdateResponse(false, exception.getMessage());
        }
    }
}
