package com.gymworkouts.gymworkouts.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "workouts_lists")
public class WorkoutListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    private UserEntity user;

    @ManyToMany
    @JoinTable(
            name="workouts_in_lists",
            joinColumns = @JoinColumn(name = "list_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "workout_id", referencedColumnName = "id")
    )
    private List<WorkoutEntity> workouts;

    public void addWorkoutToList(WorkoutEntity workout)
    {
        workouts.add(workout);
    }

    public void assignUserToList(UserEntity user)
    {
        this.user = user;
    }
}
