package com.sparta.plancalendarassignment.entity;


import com.sparta.plancalendarassignment.dto.PlanRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Plan {
    private Long planId;
    private String userId;
    private String password;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Plan(String title, String contents, String userId, String password) {
        this.title = title;
        this.contents = contents;
        this.userId = userId;
        this.password = password;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = setupdatedAt();
    }

    public Plan(Long planId, String userId, String title, String contents, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.planId = planId;
        this.userId = userId;
        this.title = title;
        this.contents = contents;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public void updatePlan(PlanRequestDto dto) {
        this.title = dto.getTitle();
        this.contents = dto.getContents();
        this.updatedAt = LocalDateTime.now();
    }

    private LocalDateTime setupdatedAt() {
        if(updatedAt == null) {
            return createdAt;
        }
        return LocalDateTime.now();
    }
}
