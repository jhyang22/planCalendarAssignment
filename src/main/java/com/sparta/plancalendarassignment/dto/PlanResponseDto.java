package com.sparta.plancalendarassignment.dto;

import com.sparta.plancalendarassignment.entity.Plan;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PlanResponseDto {
    private Long planId;
    private String userId;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PlanResponseDto(Plan plan) {
        this.planId = plan.getPlanId();
        this.userId = plan.getUserId();
        this.title = plan.getTitle();
        this.contents = plan.getContents();
        this.createdAt = plan.getCreatedAt();
        this.updatedAt = plan.getUpdatedAt();
    }

}
