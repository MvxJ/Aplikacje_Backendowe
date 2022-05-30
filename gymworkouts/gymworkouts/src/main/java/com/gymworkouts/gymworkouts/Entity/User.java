package com.gymworkouts.gymworkouts.Entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public UUID id;

    @Column(name = "username", nullable = false, unique = true)
    public String username;

    @Column(name = "password", nullable = false)
    public String password;

    @Column(name = "email", nullable = false, unique = true)
    public String email;

    @Column(name = "first_name", nullable = false)
    public String firstName;

    @Column(name = "last_name", nullable = false)
    public String lastName;

    @Column(name = "age")
    public int age;

    @Column(name = "username")
    public double weight;

    @Column(name = "height")
    public double height;

    @Column(name = "workouts_lists")
    public Set<WorkoutList> workoutsLists = new HashSet<>();
}
