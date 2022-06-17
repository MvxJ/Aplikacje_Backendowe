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
    String workoutName;
    String description;
    Long categoryId;
    String sex;
    int recomendedWeight;
    int recomendedRedemptions;
    String smallImageUrl;
    String mediumImageUrl;
    String highImageUrl;
}
