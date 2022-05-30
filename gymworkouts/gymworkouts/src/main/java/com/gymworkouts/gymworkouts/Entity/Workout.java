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
public class Workout {
    @ManyToMany
    @JoinTable(
            name="workouts_list",
            joinColumns = @JoinColumn(name = "workout_id"),
            inverseJoinColumns = @JoinColumn(name = "list_id")
    )
    private List<WorkoutList> workoutsLists;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public UUID id;

    @Column(name = "name", nullable = false, unique = true)
    public String name;

    @Column(name = "description")
    public String description;

    @Column(name = "images")
    public List<Image> images;

    @Column(name = "sex")
    public String sex;

    @Column(name = "recommended_weight")
    public int recommendedWeight;

    @Column(name = "recommended_repetitions")
    public int recommendedRepetitions;
}
