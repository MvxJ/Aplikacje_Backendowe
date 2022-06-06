package com.gymworkouts.gymworkouts.Entity;

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
    public long id;

    @Column(name = "name")
    public String name;

    @Column(name = "description")
    public String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public UserEntity user;

    @ManyToMany(mappedBy = "workoutsLists")
    public List<WorkoutEntity> workouts;
}
