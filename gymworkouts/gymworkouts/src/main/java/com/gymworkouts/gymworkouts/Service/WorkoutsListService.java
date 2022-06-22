package com.gymworkouts.gymworkouts.Service;

import com.gymworkouts.gymworkouts.Entity.UserEntity;
import com.gymworkouts.gymworkouts.Entity.WorkoutEntity;
import com.gymworkouts.gymworkouts.Entity.WorkoutListEntity;
import com.gymworkouts.gymworkouts.Repository.UserRepository;
import com.gymworkouts.gymworkouts.Repository.WorkoutsListRepository;
import com.gymworkouts.gymworkouts.Repository.WorkoutsRepository;
import com.gymworkouts.gymworkouts.Requests.CreateWorkoutListRequest;
import com.gymworkouts.gymworkouts.Requests.UpdateWorkoutListRequest;
import com.gymworkouts.gymworkouts.Responses.CreateResponse;
import com.gymworkouts.gymworkouts.Responses.DeleteResponse;
import com.gymworkouts.gymworkouts.Responses.UpdateResponse;
import com.gymworkouts.gymworkouts.Responses.WorkoutListActionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class WorkoutsListService {
    @Autowired
    private WorkoutsListRepository workoutsListRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkoutsRepository workoutsRepository;

    public CreateResponse createList(HttpServletRequest request, CreateWorkoutListRequest workoutListRequest) {
        if (this.authService.isUserLogged(request.getSession())) {
            Long userId = this.authService.getLoggedUserId(request.getSession());
            Optional<UserEntity> userOptional = this.userRepository.findById(userId);

            if (userOptional.isPresent()) {
                WorkoutListEntity workoutList = new WorkoutListEntity();
                workoutList.setName(workoutListRequest.getName());
                workoutList.setDescription(workoutListRequest.getDescription());
                workoutList.setUser(userOptional.get());

                this.workoutsListRepository.save(workoutList);

                return new CreateResponse(true, "Successfully create workout list!", workoutList);
            }

            return new CreateResponse(false, "Can't find user id!", null);
        }

        return new CreateResponse(false, "No logged in user", null);
    }

    public UpdateResponse updateList(
            Long listId,
            UpdateWorkoutListRequest updateWorkoutListRequest,
            HttpServletRequest request
    ) {
        if (this.authService.isUserLogged(request.getSession())) {
            Optional<WorkoutListEntity> foundListEntity = this.workoutsListRepository.findById(listId);

            if (foundListEntity.isPresent()) {
                WorkoutListEntity foundEntity = foundListEntity.get();
                foundEntity.setDescription(updateWorkoutListRequest.getDescription());
                foundEntity.setName(updateWorkoutListRequest.getName());

                this.workoutsListRepository.save(foundEntity);

                return new UpdateResponse(true, "Workout list was successfully updated");
            }

            return new UpdateResponse(false, "Entity not found!");
        }

        return new UpdateResponse(false, "Required logged in user!");
    }

    public DeleteResponse deleteList(Long listId, HttpServletRequest request) {
        if (this.authService.isUserLogged(request.getSession())) {
            Optional<WorkoutListEntity> foundEntity = this.workoutsListRepository.findById(listId);

            if (foundEntity.isPresent()) {
                this.workoutsListRepository.deleteById(listId);

                return new DeleteResponse(true, "Successfully deleted workout list!");
            }

            return new DeleteResponse(false, "Entity not found!");
        }

        return new DeleteResponse(false, "Required logged in user!");
    }

    public WorkoutListActionResponse addWorkoutToList(Long workoutId, Long listId, HttpServletRequest request) {
        if (this.authService.isUserLogged(request.getSession())) {
            Optional<WorkoutEntity> workout = this.workoutsRepository.findById(workoutId);
            Optional<WorkoutListEntity> list = this.workoutsListRepository.findById(listId);

            if (list.isPresent() && workout.isPresent()) {
                WorkoutEntity workoutEntity = workout.get();
                WorkoutListEntity workoutList = list.get();
                List<WorkoutEntity> workouts = workoutList.getWorkouts();
                workouts.add(workoutEntity);
                workoutList.setWorkouts(workouts);

                this.workoutsListRepository.save(workoutList);

                return new WorkoutListActionResponse(true, "Successfully added workout to list!");
            }

            return new WorkoutListActionResponse(false, "Can't find requested entity!");
        }

        return new WorkoutListActionResponse(false, "Required logged in user!");
    }

    public WorkoutListActionResponse removeWorkoutFromList(Long workoutId, Long listId, HttpServletRequest request) {
        if (this.authService.isUserLogged(request.getSession())) {
            Optional<WorkoutEntity> workout = this.workoutsRepository.findById(workoutId);
            Optional<WorkoutListEntity> list = this.workoutsListRepository.findById(listId);

            if (list.isPresent() && workout.isPresent()) {
                WorkoutEntity workoutEntity = workout.get();
                WorkoutListEntity workoutList = list.get();
                List<WorkoutEntity> workouts = workoutList.getWorkouts();
                workouts.remove(workoutEntity);
                workoutList.setWorkouts(workouts);

                this.workoutsListRepository.save(workoutList);

                return new WorkoutListActionResponse(true, "Successfully removed workout from list!");
            }

            return new WorkoutListActionResponse(false, "Can't find requested entity!");
        }

        return new WorkoutListActionResponse(false, "Required logged in user!");
    }
}
