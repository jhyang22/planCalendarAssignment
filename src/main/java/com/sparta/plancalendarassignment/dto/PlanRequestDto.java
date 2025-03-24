package com.sparta.plancalendarassignment.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PlanRequestDto {
    private String userId;
    private String password;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
}

