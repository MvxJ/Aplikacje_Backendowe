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
@Table(name = "images")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column(name = "url_small")
    public String urlSmall;

    @Column(name = "url_medium")
    public String urlMedium;

    @Column(name = "url_high")
    public String urlHigh;

    @Column(name = "workouts")
    @ManyToMany(mappedBy = "images")
    public List<WorkoutEntity> workouts;

    public String getUrlSmall() {
        return urlSmall;
    }

    public void setUrlSmall(String urlSmall) {
        this.urlSmall = urlSmall;
    }

    public String getUrlMedium() {
        return urlMedium;
    }

    public void setUrlMedium(String urlMedium) {
        this.urlMedium = urlMedium;
    }

    public String getUrlHigh() {
        return urlHigh;
    }

    public void setUrlHigh(String urlHigh) {
        this.urlHigh = urlHigh;
    }

    public List<WorkoutEntity> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<WorkoutEntity> workouts) {
        this.workouts = workouts;
    }
}
