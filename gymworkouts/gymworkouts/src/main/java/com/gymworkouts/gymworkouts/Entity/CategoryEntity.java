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
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "category")
    private List<WorkoutEntity> workouts;

    public void assignWorkout(WorkoutEntity workout) {
        this.workouts.add(workout);
    }

    public void removeWorkout(WorkoutEntity workout) {
        this.workouts.remove(workout);
    }
}
