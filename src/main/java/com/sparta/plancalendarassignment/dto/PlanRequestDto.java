package com.sparta.plancalendarassignment.dto;


import jakarta.validation.constraints.Size;
import lombok.Getter;


@Getter
public class PlanRequestDto {
    @Size(min = 1, max = 10, message = "최소 1글자, 최대 10글자까지 가능합니다.")
    private String userId;
    @Size(min = 1, max = 10, message = "최소 1글자, 최대 10글자까지 가능합니다.")
    private String password;
    @Size(min = 1, max = 10, message = "최소 1글자, 최대 10글자까지 가능합니다.")
    private String title;
    @Size(min = 1, max = 50, message = "최소 1글자, 최대 50글자까지 가능합니다.")
    private String contents;
}

