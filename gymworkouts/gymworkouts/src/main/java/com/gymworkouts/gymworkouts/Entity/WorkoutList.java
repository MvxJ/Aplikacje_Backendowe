package com.gymworkouts.gymworkouts.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public UUID userId;

    @ManyToMany(mappedBy = "workoutsLists")
    public List<Workout> workoutsList;
}
