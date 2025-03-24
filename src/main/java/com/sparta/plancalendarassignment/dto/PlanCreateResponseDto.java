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
// dto는 데이터를 보정하는 작업을 하면 안됨
// 전달만 하는 목적

// put을 안해서