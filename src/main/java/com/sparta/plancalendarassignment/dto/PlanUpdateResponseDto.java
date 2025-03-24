package com.sparta.plancalendarassignment.dto;

import com.sparta.plancalendarassignment.entity.Plan;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PlanUpdateResponseDto {
    private Long planId;
    private String userId;
    private String title;
    private String contents;
    private LocalDateTime updatedAt;

    public PlanUpdateResponseDto(Plan plan) {
        this.planId = plan.getPlanId();
        this.userId = plan.getUserId();
        this.title = plan.getTitle();
        this.contents = plan.getContents();
        this.updatedAt = plan.getUpdatedAt();
    }
}
