package com.gymworkouts.gymworkouts.Responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckAccessResponse {
    private boolean status;
    private Long userId;
}
