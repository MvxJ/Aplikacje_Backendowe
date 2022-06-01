package com.gymworkouts.gymworkouts.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

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
    public long id;

//    @ManyToMany(mappedBy = "workoutsLists")
//    public List<WorkoutEntity> workoutsList;
}
