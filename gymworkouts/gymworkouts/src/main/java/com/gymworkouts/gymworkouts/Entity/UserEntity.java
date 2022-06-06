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
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

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

    @Column(name = "weight")
    public double weight;

    @Column(name = "height")
    public double height;

    @OneToMany(cascade = CascadeType.ALL)
    public List<WorkoutListEntity> workoutsLists;
}
