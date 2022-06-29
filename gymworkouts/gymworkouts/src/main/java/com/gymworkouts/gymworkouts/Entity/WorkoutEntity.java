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
@Table(name = "workouts")
public class WorkoutEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "workouts_images",
            joinColumns = @JoinColumn(name = "image_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "workout_id", referencedColumnName = "id")
    )
    private List<ImageEntity> images;

    @Column(name = "sex")
    private String sex;

    @Column(name = "recommended_weight")
    private int recommendedWeight;

    @Column(name = "recommended_repetitions")
    private int recommendedRepetitions;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "id"
    )
    private CategoryEntity category;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name="workouts_list_in_lists",
            joinColumns = @JoinColumn(name = "list_id"),
            inverseJoinColumns = @JoinColumn(name = "workout_id")
    )
    private List<WorkoutListEntity> workoutsLists;

    @Column(name = "cat_id")
    private long categoryId;

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
