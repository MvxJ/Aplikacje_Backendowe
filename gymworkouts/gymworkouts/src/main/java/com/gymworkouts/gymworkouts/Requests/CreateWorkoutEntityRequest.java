package com.gymworkouts.gymworkouts.Requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateWorkoutEntityRequest {
    private String workoutName;
    private String description;
    private String sex;
    private int recomendedWeight;
    private int recomendedRedemptions;
    private String smallImageUrl;
    private String mediumImageUrl;
    private String highImageUrl;
}
