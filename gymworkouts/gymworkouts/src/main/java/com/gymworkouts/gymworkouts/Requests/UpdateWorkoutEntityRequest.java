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
    private String workoutName;
    private String description;
    private int recomendedWeight;
    private int recomendedRedemption;
    private String sex;
}
