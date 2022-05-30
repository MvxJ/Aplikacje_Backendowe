package com.gymworkouts.gymworkouts.Entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public UUID id;

    @Column(name = "name", nullable = false, unique = true)
    public String name;

    @Column(name = "description")
    public String description;

    @Column(name = "workouts")
    public List<Workout> workouts;
}
