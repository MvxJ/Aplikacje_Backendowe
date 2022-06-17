package com.gymworkouts.gymworkouts.Requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWorkoutEntityRequest {
    String workoutName;
    String description;
    int recomendedWeight;
    int recomendedRedemption;
    String urlSmall;
    String urlMedium;
    String urlHigh;
    String sex;
    Long categoryId;
}
