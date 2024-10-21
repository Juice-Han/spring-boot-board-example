package com.mysite.board_example.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CheckIdDuplicationResponse {
    private String checkingId;
    private Boolean isPossible;
}
