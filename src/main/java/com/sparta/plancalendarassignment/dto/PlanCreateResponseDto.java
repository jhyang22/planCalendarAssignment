package com.sparta.plancalendarassignment.dto;

import com.sparta.plancalendarassignment.entity.Plan;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PlanCreateResponseDto {
    private Long planId;
    private String title;
    private String contents;
    private LocalDateTime createdAt;

    public PlanCreateResponseDto(Plan plan) {
        this.planId = plan.getPlanId();
        this.title = plan.getTitle();
        this.contents = plan.getContents();
        this.createdAt = plan.getCreatedAt();
    }
}