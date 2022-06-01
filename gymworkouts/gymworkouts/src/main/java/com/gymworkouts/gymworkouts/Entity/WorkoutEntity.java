package com.gymworkouts.gymworkouts.Entity;

import com.gymworkouts.gymworkouts.Repository.WorkoutsRepository;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "workouts")
public class WorkoutEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column(name = "name", nullable = false, unique = true)
    public String name;

    @Column(name = "description")
    public String description;

//    @Column(name = "images")
//    public List<ImageEntity> images;

    @Column(name = "sex")
    public String sex;

    @Column(name = "recommended_weight")
    public int recommendedWeight;

    @Column(name = "recommended_repetitions")
    public int recommendedRepetitions;

//    @ManyToMany
//    @JoinTable(
//            name="workouts_list",
//            joinColumns = @JoinColumn(name = "workout_id"),
//            inverseJoinColumns = @JoinColumn(name = "list_id")
//    )
//    public List<WorkoutListEntity> workoutsLists;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getRecommendedWeight() {
        return recommendedWeight;
    }

    public void setRecommendedWeight(int recommendedWeight) {
        this.recommendedWeight = recommendedWeight;
    }

    public int getRecommendedRepetitions() {
        return recommendedRepetitions;
    }

    public void setRecommendedRepetitions(int recommendedRepetitions) {
        this.recommendedRepetitions = recommendedRepetitions;
    }
}
